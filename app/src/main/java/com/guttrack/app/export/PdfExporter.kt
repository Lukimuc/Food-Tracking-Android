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
                    val photoW = if (includePhotos && item.photoUri != null) 160f else 0f
                    val photoH = if (includePhotos && item.photoUri != null) 120f else 0f
                    val drinkPhotoW = if (includePhotos && item.drinkPhotoUri != null) 160f else 0f
                    val drinkPhotoH = if (includePhotos && item.drinkPhotoUri != null) 120f else 0f
                    
                    val maxPhotoHeight = maxOf(photoH, drinkPhotoH)
                    
                    newPageIfNeeded(80f + maxPhotoHeight)
                    val startY = y
                    var textX = 40f

                    if (photoW > 0f) {
                        drawPhoto(context, canvas, item.photoUri!!, RectF(textX, y, textX + photoW, y + photoH))
                        textX += photoW + 8f
                    }
                    
                    if (drinkPhotoW > 0f) {
                        drawPhoto(context, canvas, item.drinkPhotoUri!!, RectF(textX, y, textX + drinkPhotoW, y + drinkPhotoH))
                        textX += drinkPhotoW + 8f
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

    private fun drawPhoto(context: Context, canvas: android.graphics.Canvas, uri: String, rect: RectF) {
        val bmp = decodeBitmap(context, uri) ?: return
        val bmpW = bmp.width.toFloat()
        val bmpH = bmp.height.toFloat()
        val bmpRatio = bmpW / bmpH
        val rectRatio = rect.width() / rect.height()

        val srcRect = if (bmpRatio > rectRatio) {
            // Bitmap is wider than target rect
            val newW = bmpH * rectRatio
            val left = (bmpW - newW) / 2
            android.graphics.Rect(left.toInt(), 0, (left + newW).toInt(), bmpH.toInt())
        } else {
            // Bitmap is taller than target rect
            val newH = bmpW / rectRatio
            val top = (bmpH - newH) / 2
            android.graphics.Rect(0, top.toInt(), bmpW.toInt(), (top + newH).toInt())
        }
        canvas.drawBitmap(bmp, srcRect, rect, null)
    }

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
