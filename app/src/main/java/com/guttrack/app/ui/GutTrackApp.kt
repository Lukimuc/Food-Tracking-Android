package com.guttrack.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guttrack.app.R
import com.guttrack.app.ui.export.ExportScreen
import com.guttrack.app.ui.home.HomeScreen
import com.guttrack.app.ui.logmeal.LogMealScreen
import com.guttrack.app.ui.settings.SettingsScreen
import com.guttrack.app.ui.symptom.SymptomScreen
import com.guttrack.app.ui.theme.GtOnSurfaceFaint
import com.guttrack.app.ui.theme.GtPrimary
import com.guttrack.app.ui.theme.GtPrimaryDark
import com.guttrack.app.ui.timeline.TimelineScreen
import com.guttrack.app.viewmodel.GutTrackViewModel
import com.guttrack.app.viewmodel.Modal
import com.guttrack.app.viewmodel.Tab

@Composable
fun GutTrackApp(viewModel: GutTrackViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                if (uiState.modal == null) {
                    BottomNavBar(currentTab = uiState.tab, onSelect = { viewModel.setTab(it) })
                }
            },
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 18.dp),
            ) {
                when (uiState.tab) {
                    Tab.HOME -> HomeScreen(viewModel)
                    Tab.TIMELINE -> TimelineScreen(viewModel)
                    Tab.EXPORT -> ExportScreen(viewModel)
                    Tab.SETTINGS -> SettingsScreen(viewModel)
                }
            }
        }

        when (uiState.modal) {
            Modal.LOG -> Box(modifier = Modifier.fillMaxSize()) { LogMealScreen(viewModel) }
            Modal.SYMPTOM -> Box(modifier = Modifier.fillMaxSize()) { SymptomScreen(viewModel) }
            null -> Unit
        }
    }
}

@Composable
private fun BottomNavBar(currentTab: Tab, onSelect: (Tab) -> Unit) {
    val items = listOf(
        Triple(Tab.HOME, stringResource(R.string.tab_home), Icons.Filled.Home),
        Triple(Tab.TIMELINE, stringResource(R.string.tab_timeline), Icons.Filled.Schedule),
        Triple(Tab.EXPORT, stringResource(R.string.tab_export), Icons.Filled.IosShare),
        Triple(Tab.SETTINGS, stringResource(R.string.tab_settings), Icons.Filled.Settings),
    )
    Row(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(999.dp))
            .padding(6.dp),
    ) {
        items.forEach { (tab, label, icon) ->
            val active = tab == currentTab
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelect(tab) }
                    .background(if (active) com.guttrack.app.ui.theme.GtChipBg else Color.Transparent, RoundedCornerShape(999.dp))
                    .padding(vertical = 9.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(icon, contentDescription = label, tint = if (active) GtPrimaryDark else GtOnSurfaceFaint, modifier = Modifier.size(21.dp))
                Text(label, fontSize = 10.5.sp, fontWeight = FontWeight.SemiBold, color = if (active) GtPrimaryDark else GtOnSurfaceFaint, modifier = Modifier.padding(top = 1.dp))
            }
        }
    }
}
