package com.example.quickpay.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.events.ServiceEvent
import com.example.serviceModels.Services

val services = listOf(
    Services.Airtime,
    Services.Betting,
    Services.Data,
    Services.Electricity,
    Services.Insurance,
    Services.Loan,
    Services.TV,
    Services.More
)

@Composable
fun ServiceItems(
    service: Services,
    modifier: Modifier = Modifier,
    onClick: (Services) -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick(service) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = service.iconDrawable),
            contentDescription = service.label,
            modifier = Modifier.size(36.dp),
            tint = service.tint
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = service.label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}
@Composable
fun ServiceSection(
    services: List<Services>,
    onServiceClick: (ServiceEvent) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White, // white background
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // space from screen edges
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(services) { service ->
                ServiceItems(service) {
                    onServiceClick(it.event)
                }
            }
        }
    }
}



