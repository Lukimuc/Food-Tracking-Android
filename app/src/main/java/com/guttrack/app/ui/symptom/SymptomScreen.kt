package com.guttrack.app.ui.symptom

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guttrack.app.R
import com.guttrack.app.ui.theme.GtChipBg
import com.guttrack.app.ui.theme.GtOnSurfaceFaint
import com.guttrack.app.ui.theme.GtPrimary
import com.guttrack.app.ui.theme.GtPrimaryDark
import com.guttrack.app.ui.theme.severityColor
import com.guttrack.app.viewmodel.GutTrackViewModel

@Composable
fun SymptomScreen(viewModel: GutTrackViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler { viewModel.closeModal() }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).statusBarsPadding()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(stringResource(R.string.symptom_title), fontSize = 19.sp, fontWeight = FontWeight.Bold)
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(GtChipBg)
                        .clickable { viewModel.closeModal() },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Filled.Close, contentDescription = "Close", tint = GtPrimaryDark)
                }
            }

            Text(stringResource(R.string.symptom_severity), fontSize = 12.5.sp, fontWeight = FontWeight.SemiBold, color = GtOnSurfaceFaint, modifier = Modifier.padding(bottom = 4.dp))
            Text(stringResource(R.string.symptom_scale), fontSize = 11.5.sp, color = GtOnSurfaceFaint, modifier = Modifier.padding(bottom = 14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                for (n in 1..5) {
                    val active = n <= uiState.severity
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { viewModel.onSeverityChange(n) }
                            .background(if (active) severityColor(n) else GtChipBg, RoundedCornerShape(16.dp))
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            "$n", fontSize = 15.sp, fontWeight = FontWeight.Bold,
                            color = if (active) Color.White else GtOnSurfaceFaint,
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(stringResource(R.string.symptom_mild), fontSize = 11.sp, color = GtOnSurfaceFaint)
                Text(stringResource(R.string.symptom_severe), fontSize = 11.sp, color = GtOnSurfaceFaint)
            }

            Text(stringResource(R.string.symptom_note_label), fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = GtOnSurfaceFaint, modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = uiState.symptomNote,
                onValueChange = { viewModel.onSymptomNoteChange(it) },
                placeholder = { Text(stringResource(R.string.symptom_note_placeholder)) },
                modifier = Modifier.fillMaxWidth().height(100.dp),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp)
                .clickable { viewModel.saveSymptom() }
                .background(GtPrimary, RoundedCornerShape(999.dp))
                .padding(14.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(stringResource(R.string.symptom_save), color = Color.White, fontSize = 14.5.sp, fontWeight = FontWeight.Bold)
        }
    }
}
