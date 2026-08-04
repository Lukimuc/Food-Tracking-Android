package com.guttrack.app.export

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import com.guttrack.app.R
import java.io.File
import java.io.FileOutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class ExportItem(
    val label: String,
    val time: String,
    val note: String,
    val drinkNote: String = "",
    val intoleranceTags: List<String> = emptyList(),
    val photoUri: String?,
    val drinkPhotoUri: String? = null,
    val severity: Int?,
)

data class ExportDayGroup(val label: String, val items: List<ExportItem>)

object PdfExporter {
    private val SEVERITY_COLORS = listOf(0xFF4A7CFEL, 0xFF7C6FE0L, 0xFF5468D4L, 0xFFD9668FL, 0xFFF2495AL)

    suspend fun generate(context: Context, groups: List<ExportDayGroup>, includePhotos: Boolean): File =
        withContext(Dispatchers.IO) {
            val pageWidth = 595
            val pageHeight = 842
            val pdf = PdfDocument()
            var pageNumber = 1
            var page = pdf.startPage(PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create())
            var canvas = page.canvas

            val titlePaint = Paint().apply { textSize = 20f; isFakeBoldText = true; color = android.graphics.Color.BLACK }
            val headerPaint = Paint().apply { textSize = 12f; isFakeBoldText = true; color = android.graphics.Color.rgb(46, 79, 209) }
            val labelPaint = Paint().apply { textSize = 12f; isFakeBoldText = true; color = android.graphics.Color.BLACK }
            val notePaint = Paint().apply { textSize = 11f; color = android.graphics.Color.DKGRAY }
            val timePaint = Paint().apply { textSize = 10f; color = android.graphics.Color.GRAY }
            val severityTextPaint = Paint().apply { textSize = 10f; isFakeBoldText = true; color = android.graphics.Color.WHITE }
            val dividerPaint = Paint().apply { color = android.graphics.Color.LTGRAY }

            var y = 44f
            canvas.drawText(context.getString(R.string.pdf_title), 40f, y, titlePaint)
            y += 30f

            fun newPageIfNeeded(needed: Float) {
                if (y + needed > pageHeight - 40) {
                    pdf.finishPage(page)
                    pageNumber++
                    page = pdf.startPage(PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create())
                    canvas = page.canvas
                    y = 40f
                }
            }

            for (group in groups) {
                newPageIfNeeded(30f)
                canvas.drawText(group.label.uppercase(), 40f, y, headerPaint)
                y += 18f

                for (item in group.items) {
                    val photoSize = if (includePhotos && item.photoUri != null) 60f else 0f
                    val drinkPhotoSize = if (includePhotos && item.drinkPhotoUri != null) 60f else 0f
                    val maxPhotoHeight = maxOf(photoSize, drinkPhotoSize)
                    
                    newPageIfNeeded(60f + maxPhotoHeight)
                    val startY = y
                    var textX = 40f

                    if (photoSize > 0f) {
                        val bmp = decodeBitmap(context, item.photoUri)
                        if (bmp != null) {
                            canvas.drawBitmap(bmp, null, RectF(textX, y, textX + photoSize, y + photoSize), null)
                        }
                        textX += photoSize + 8f
                    }
                    
                    if (drinkPhotoSize > 0f) {
                        val bmp = decodeBitmap(context, item.drinkPhotoUri)
                        if (bmp != null) {
                            canvas.drawBitmap(bmp, null, RectF(textX, y, textX + drinkPhotoSize, y + drinkPhotoSize), null)
                        }
                        textX += drinkPhotoSize + 8f
                    }

                    if (textX > 40f) textX += 4f // spacing before text

                    canvas.drawText(item.label, textX, y + 12f, labelPaint)
                    val timeWidth = timePaint.measureText(item.time)
                    canvas.drawText(item.time, pageWidth - 40f - timeWidth, y + 12f, timePaint)

                    var lineY = y + 28f
                    if (item.note.isNotBlank()) {
                        val wrapped = wrapText("${context.getString(R.string.pdf_food_prefix)}${item.note}", notePaint, pageWidth - textX - 40f)
                        for (line in wrapped) {
                            canvas.drawText(line, textX, lineY, notePaint)
                            lineY += 14f
                        }
                    }
                    if (item.drinkNote.isNotBlank()) {
                        val wrapped = wrapText("${context.getString(R.string.pdf_drink_prefix)}${item.drinkNote}", notePaint, pageWidth - textX - 40f)
                        for (line in wrapped) {
                            canvas.drawText(line, textX, lineY, notePaint)
                            lineY += 14f
                        }
                    }
                    if (item.intoleranceTags.isNotEmpty()) {
                        val tagPaint = Paint().apply { textSize = 9f; isFakeBoldText = true; color = android.graphics.Color.rgb(220, 80, 80) }
                        val tagsText = item.intoleranceTags.joinToString(", ")
                        val wrapped = wrapText("Intolerances: $tagsText", tagPaint, pageWidth - textX - 40f)
                        for (line in wrapped) {
                            canvas.drawText(line, textX, lineY, tagPaint)
                            lineY += 12f
                        }
                    }
                    if (item.severity != null) {
                        val badgeText = "Severity ${item.severity}/5"
                        val bw = severityTextPaint.measureText(badgeText) + 16f
                        val badgePaint = Paint().apply { color = severityColorInt(item.severity) }
                        canvas.drawRoundRect(RectF(textX, lineY, textX + bw, lineY + 16f), 8f, 8f, badgePaint)
                        canvas.drawText(badgeText, textX + 8f, lineY + 12f, severityTextPaint)
                        lineY += 22f
                    }

                    y = maxOf(startY + maxPhotoHeight + 10f, lineY + 8f)
                    canvas.drawLine(40f, y, pageWidth - 40f, y, dividerPaint)
                    y += 12f
                }
                y += 8f
            }

            pdf.finishPage(page)

            val dir = File(context.cacheDir, "exports").apply { mkdirs() }
            val file = File(dir, "GutTrack_Report.pdf")
            FileOutputStream(file).use { pdf.writeTo(it) }
            pdf.close()
            file
        }

    fun uriFor(context: Context, file: File): Uri =
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)

    private fun decodeBitmap(context: Context, uriString: String?): Bitmap? {
        if (uriString == null) return null
        return try {
            context.contentResolver.openInputStream(Uri.parse(uriString))?.use { BitmapFactory.decodeStream(it) }
        } catch (e: Exception) {
            null
        }
    }

    private fun wrapText(text: String, paint: Paint, maxWidth: Float): List<String> {
        val words = text.split(" ")
        val lines = mutableListOf<String>()
        var current = StringBuilder()
        for (w in words) {
            val candidate = if (current.isEmpty()) w else "$current $w"
            if (paint.measureText(candidate) > maxWidth && current.isNotEmpty()) {
                lines.add(current.toString())
                current = StringBuilder(w)
            } else {
                current = StringBuilder(candidate)
            }
        }
        if (current.isNotEmpty()) lines.add(current.toString())
        return lines
    }

    private fun severityColorInt(n: Int): Int = SEVERITY_COLORS[n.coerceIn(1, 5) - 1].toInt()
}
