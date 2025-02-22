/*
 * Copyright 2022, Lawnchair
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.lawnchair.ui.preferences.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.lawnchair.preferences2.PreferenceCollectorScope
import app.lawnchair.preferences2.PreferenceManager2

@Composable
fun PreferenceCollectorScope.SwitchPreference2(
    checked: Boolean,
    label: String,
    description: String? = null,
    enabled: Boolean = true,
    edit: suspend PreferenceManager2.(Boolean) -> Unit,
    showDivider: Boolean = false,
) {
    SwitchPreference2(
        checked = checked,
        onCheckedChange = { edit { this.edit(it) } },
        label = label,
        description = description,
        enabled = enabled,
        showDivider = showDivider,
    )
}

@Composable
fun SwitchPreference2(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    description: String? = null,
    enabled: Boolean = true,
    showDivider: Boolean = false,
) {
    PreferenceTemplate(
        title = { Text(text = label) },
        showDivider = showDivider,
        description = { description?.let { Text(text = it) } },
        enabled = enabled,
        endWidget = {
            Switch(
                modifier = Modifier.height(24.dp),
                checked = checked,
                enabled = enabled,
                colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary),
                onCheckedChange = { onCheckedChange(it) },
            )
        },
        modifier = Modifier.clickable(enabled) {
            onCheckedChange(!checked)
        },
    )
}
