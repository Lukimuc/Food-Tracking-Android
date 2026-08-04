package com.guttrack.app.export;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\bJ\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J&\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00052\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/guttrack/app/export/PdfExporter;", "", "<init>", "()V", "SEVERITY_COLORS", "", "", "generate", "Ljava/io/File;", "context", "Landroid/content/Context;", "groups", "Lcom/guttrack/app/export/ExportDayGroup;", "includePhotos", "", "(Landroid/content/Context;Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uriFor", "Landroid/net/Uri;", "file", "decodeBitmap", "Landroid/graphics/Bitmap;", "uriString", "", "wrapText", "text", "paint", "Landroid/graphics/Paint;", "maxWidth", "", "severityColorInt", "", "n", "app_debug"})
public final class PdfExporter {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.Long> SEVERITY_COLORS = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.guttrack.app.export.PdfExporter INSTANCE = null;
    
    private PdfExporter() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generate(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.guttrack.app.export.ExportDayGroup> groups, boolean includePhotos, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.net.Uri uriFor(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File file) {
        return null;
    }
    
    private final android.graphics.Bitmap decodeBitmap(android.content.Context context, java.lang.String uriString) {
        return null;
    }
    
    private final java.util.List<java.lang.String> wrapText(java.lang.String text, android.graphics.Paint paint, float maxWidth) {
        return null;
    }
    
    private final int severityColorInt(int n) {
        return 0;
    }
}