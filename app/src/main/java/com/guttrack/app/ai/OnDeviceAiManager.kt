package com.guttrack.app.ai

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.mlkit.genai.prompt.*
import com.google.mlkit.genai.common.StreamingCallback
import kotlinx.coroutines.tasks.await
import org.json.JSONObject

class OnDeviceAiManager(private val context: Context) {
    private val model = Generation.getClient()

    /** 
     * Identifies food and drink in a list of images.
     */
    suspend fun describeMeal(uriStrings: List<String>, language: String): String? {
        if (uriStrings.isEmpty()) return null
        val langName = if (language == "de") "German" else "English"
        
        // To handle multiple images on-device with the Prompt API, 
        // we'll describe each image and then merge the results if there are multiple.
        val descriptions = mutableListOf<String>()
        for (uriStr in uriStrings) {
            val desc = describeSingleImage(uriStr, langName)
            if (desc != null) descriptions.add(desc)
        }

        if (descriptions.isEmpty()) return null
        if (descriptions.size == 1) return descriptions.first()

        // Merge multiple descriptions
        return mergeDescriptions(descriptions, langName)
    }

    private suspend fun describeSingleImage(uriStr: String, langName: String): String? {
        return try {
            val uri = Uri.parse(uriStr)
            val bitmap = context.contentResolver.openInputStream(uri)?.use {
                BitmapFactory.decodeStream(it)
            } ?: return null

            val prompt = "Identify all food and drinks in this image. List major ingredients. Do NOT describe the background, furniture, tables, or any context. Return ONLY a concise list of food items and their ingredients. Respond in $langName."

            val request = GenerateContentRequest.builder(
                ImagePart(bitmap),
                TextPart(prompt)
            ).build()

            val response = model.generateContent(request, object : StreamingCallback {
                override fun onNewText(text: String) {}
            })
            response.candidates.firstOrNull()?.text?.replace("**", "")?.trim()
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun mergeDescriptions(descriptions: List<String>, langName: String): String? {
        return try {
            val prompt = "Combine these separate food/drink descriptions into one concise, non-redundant list of items and ingredients: ${descriptions.joinToString("; ")}. Respond in $langName."
            val request = GenerateContentRequest.builder(TextPart(prompt)).build()
            val response = model.generateContent(request, object : StreamingCallback {
                override fun onNewText(text: String) {}
            })
            response.candidates.firstOrNull()?.text?.replace("**", "")?.trim()
        } catch (e: Exception) {
            descriptions.joinToString(", ")
        }
    }

    /**
     * Analyzes text for allergens and intolerances.
     */
    suspend fun checkIntolerances(text: String, language: String): List<String> {
        if (text.isBlank()) return emptyList()
        val langName = if (language == "de") "German" else "English"
        return try {
            val prompt = """
                Analyze the following food/drink for allergens and intolerances: "$text"
                
                Search for matches from this specific list:
                1. EU Major Allergens: Gluten, Crustaceans, Eggs, Fish, Peanuts, Soybeans, Milk, Lactose, Nuts, Celery, Mustard, Sesame, Sulphites, Lupin, Molluscs.
                2. Common Intolerances: Fructose, Histamine, FODMAPs, Artificial Additives.
                
                Return ONLY the matched categories as a simple comma-separated list. 
                Do NOT use JSON. Do NOT use markdown. Do NOT use bolding. 
                Use $langName for the names. If no matches, return "None".
            """.trimIndent()

            val request = GenerateContentRequest.builder(TextPart(prompt)).build()
            val response = model.generateContent(request, object : StreamingCallback {
                override fun onNewText(text: String) {}
            })
            
            val result = response.candidates.firstOrNull()?.text ?: return emptyList()
            if (result.contains("None", ignoreCase = true)) return emptyList()
            
            result.split(",")
                .map { it.trim().replace(".", "").replace("*", "") }
                .filter { it.isNotBlank() && it.length > 2 }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
