package com.guttrack.app.ui.components;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a.\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\n2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a3\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\bH\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001a^\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u001726\u0010\u0018\u001a2\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00010\u0019H\u0007\u001a\u000e\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003\u00a8\u0006 "}, d2 = {"SectionLabel", "", "text", "", "modifier", "Landroidx/compose/ui/Modifier;", "GtSwitch", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "SeverityBadge", "severity", "", "color", "Landroidx/compose/ui/graphics/Color;", "small", "SeverityBadge-RPmYEkk", "(IJLandroidx/compose/ui/Modifier;Z)V", "TimePickerDialog", "initialHour", "initialMinute", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "hour", "minute", "formatTime24to12", "hhmm", "app_debug"})
public final class CommonComponentsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SectionLabel(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void GtSwitch(boolean checked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onCheckedChange, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void TimePickerDialog(int initialHour, int initialMinute, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> onConfirm) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatTime24to12(@org.jetbrains.annotations.NotNull()
    java.lang.String hhmm) {
        return null;
    }
}