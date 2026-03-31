package com.example.polyline_polygon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.Polygon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.padding

@Composable
fun TrailMapScreen(modifier: Modifier = Modifier) {

    val trailStart = LatLng(42.3505, -71.1054)

    val trailPoints = listOf(
        LatLng(42.3504, -71.1088),
        LatLng(42.3503, -71.1076),
        LatLng(42.3502, -71.1064),
        LatLng(42.3501, -71.1052)
    )

    val parkPoints = listOf(
        LatLng(42.3505, -71.1072),
        LatLng(42.3513, -71.1072),
        LatLng(42.3513, -71.1058),
        LatLng(42.3505, -71.1058)
    )

    var lineColor by remember { mutableStateOf(Color.Blue) }

    var lineWidth by remember { mutableStateOf(12f) }

    var infoText by remember { mutableStateOf("Tap something") }

    var polygonColor by remember { mutableStateOf(Color.Green.copy(alpha = 0.3f)) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(trailStart, 15f)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { lineColor = Color.Red }) {
                Text("Red Line")
            }
            Button(onClick = { lineColor = Color.Blue }) {
                Text("Blue Line")
            }
            Button(onClick = { lineWidth += 4f }) {
                Text("Thicker")
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { polygonColor = Color.Green.copy(alpha = 0.3f) }) {
                Text("Green Area")
            }
            Button(onClick = { polygonColor = Color.Yellow.copy(alpha = 0.3f) }) {
                Text("Yellow Area")
            }
        }

        Text(
            text = infoText,
            modifier = Modifier.padding(8.dp)
        )

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Polyline(
                points = trailPoints,
                width = lineWidth,
                color = lineColor,
                clickable = true,
                onClick = {
                    infoText = "This is a hiking trail"
                }
            )

            Polygon(
                points = parkPoints,
                fillColor = polygonColor,
                clickable = true,
                onClick = {
                    infoText = "This is a park area"
                }
            )
        }
    }
}