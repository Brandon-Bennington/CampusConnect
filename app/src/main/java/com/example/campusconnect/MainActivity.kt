package com.example.campusconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

// Store resource IDs so Android can resolve strings for the current language.
data class CampusResource(
    @StringRes val nameRes: Int,
    @StringRes val categoryRes: Int,
    @StringRes val hoursRes: Int,
    val lastUpdate: String
)

val sampleResources = listOf(
    CampusResource(
        R.string.resource_intl_office_name,
        R.string.resource_intl_office_category,
        R.string.resource_intl_office_hours,
        "03/08/2026"
    ),
    CampusResource(
        R.string.resource_library_name,
        R.string.resource_library_category,
        R.string.resource_library_hours,
        "03/08/2026"
    ),
    CampusResource(
        R.string.resource_veterans_name,
        R.string.resource_veterans_category,
        R.string.resource_veterans_hours,
        "03/08/2026"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CampusConnectScreen()
            }
        }
    }
}

@Composable
fun CampusConnectScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(stringResource(R.string.app_title), style = MaterialTheme.typography.headlineMedium)
        Text(stringResource(R.string.app_subtitle), style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(sampleResources) { resource ->
                ResourceCard(resource)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ResourceCard(resource: CampusResource) {
    val name = stringResource(resource.nameRes)
    val category = stringResource(resource.categoryRes)
    val hours = stringResource(resource.hoursRes)
    // Prepared in class for an accessible card description.
    @Suppress("UNUSED_VARIABLE")
    val contentDesc = stringResource(R.string.content_desc_resource_card, name, category, hours)
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(name, style = MaterialTheme.typography.titleMedium)
            Text(stringResource(R.string.category_label, category), style = MaterialTheme.typography.bodySmall)
            Text(stringResource(R.string.hours_label, hours))
            Text(stringResource(R.string.last_updated_label, resource.lastUpdate), style = MaterialTheme.typography.bodySmall)
        }
    }
}
