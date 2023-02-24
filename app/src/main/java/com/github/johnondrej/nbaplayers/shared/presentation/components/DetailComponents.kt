package com.github.johnondrej.nbaplayers.shared.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.johnondrej.nbaplayers.R

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(all = 16.dp)
    ) {
        CircularProgressIndicator()

        Text(
            text = stringResource(id = R.string.general_loading),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun DetailTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h5,
        modifier = modifier
    )
}

@Composable
fun DetailParameter(
    name: String,
    value: String?,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .let {
                if (onClick != null) {
                    it.clickable(onClick = onClick)
                } else {
                    it
                }
            }
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "${name}:",
            modifier = Modifier.weight(1f)
        )

        val valueText = if (onClick != null) "$value >" else value
        Text(
            text = valueText ?: stringResource(id = R.string.parameter_unknown),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ErrorMessage(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(all = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.general_download_error),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onRetryClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.general_retry))
        }
    }
}
