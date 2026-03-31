package com.example.quickpay.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickpay.AppBar
import com.example.quickpay.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun QuickPayCardScreen(
    navigator: DestinationsNavigator
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Physical Card", "Virtual Card")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F0F))
    ) {
        // Use your custom AppBar
        AppBar(
            title = "QuickPay Cards",
            navigator = navigator,
            showMenu = true
        )

        // Tab Row
        val isVirtualTab = selectedTab == 1

        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = if (isVirtualTab) Color(0xFF6DB6FF) else Color(0xFF0F0F0F),
            indicator = { tabPositions ->
                if (selectedTab < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        height = 3.dp,
                        color = if (isVirtualTab) Color(0xFFF4FF81) else Color(0xFFFFD54F)
                    )
                }
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTab == index
                val selectedColor = if (selectedTab == 0) {
                    Color(0xFFFFD54F) // Physical
                } else {
                    Color(0xFFF4FF81) // Virtual
                }

                Tab(
                    selected = isSelected,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = if (isSelected) 18.sp else 16.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) selectedColor else Color.White
                        )
                    }
                )
            }
        }

        // Content based on selected tab
        when (selectedTab) {
            0 -> PhysicalCardContent()
            1 -> VirtualCardContent()
        }
    }
}

// ============================================
//           PHYSICAL CARD
// ============================================
private @Composable
fun PhysicalCardContent() {
    var isChecked by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        PhysicalCardShowcase()
        Spacer(modifier = Modifier.height(16.dp))
        PhysicalCreditInfoSection()
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "More Card Benefits",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        PhysicalBenefitsGrid()
        Spacer(modifier = Modifier.height(4.dp))
        PhysicalFooterText()
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .padding(top = 12.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFFFFD54F),
                        uncheckedColor = Color.Gray
                    )
                )
            }

            Text(
                text = "I’ve read and consented Flexi Cash terms & conditions and privacy policy",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                modifier = Modifier
                    .weight(1f)
                    .clickable { isChecked = !isChecked }
                    .padding(top = 12.dp),
                lineHeight = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        PhysicalBottomCTAButton()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

private @Composable
fun PhysicalCardShowcase() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "All-In-One: Debit & Credit",
                color = Color(0xFFFFD54F),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.quickpay_card),
                    contentDescription = "Card",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "QuickPay",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Verve",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 13.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.wrapContentWidth()
        ) {
            Text(
                text = "Verve Premium Card",
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

private @Composable
fun PhysicalCreditInfoSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF3A3320)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Up to ₦500,000 Credit limit,\nlowest to 0.1% Daily Interest",
                color = Color(0xFFFFE082),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF3A3320)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Exclusive for Premium Members",
                color = Color(0xFFFFE082),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            )
        }
    }
}

private data class PhysicalBenefit(val title: String, val description: String)

private @Composable
fun PhysicalBenefitsGrid() {
    val benefits = listOf(
        PhysicalBenefit("Low Daily \nInterest", "As low \nas 0.1%"),
        PhysicalBenefit("Free \nService Fee", "On all credit \npayments"),
        PhysicalBenefit("Zero \nMaintenance Fee", "For you"),
        PhysicalBenefit("CardCare+ \nService", "Free card \nreplacement"),
        PhysicalBenefit("Instant \nRefunds", "On failed \ntransactions"),
        PhysicalBenefit("Accepted \nBy 40,000+", "Online \nmerchants")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(benefits.size) { index ->
            PhysicalBenefitCard(benefits[index])
        }
    }
}

private @Composable
fun PhysicalBenefitCard(benefit: PhysicalBenefit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = benefit.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = benefit.description,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 13.5.sp
            )
        }
    }
}

private @Composable
fun PhysicalFooterText() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Card credit services are provided by Flexi MFB Ltd.",
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Licensed by CBN as MMO 🏦",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Deposits Insured by NDIC",
                color = Color(0xFFFFD54F),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private @Composable
fun PhysicalBottomCTAButton() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "🎁 Enjoy ₦3000 Coupon Offer expires at 05:31:51",
                color = Color(0xFFFFE082),
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Get Card Lowest to ₦499",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ============================================
//             VIRTUAL CARD
// ============================================

@Composable
fun VirtualCardContent() {
//    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF6DB6FF), Color(0xFFF5EBD7))
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        VirtualCardShowcase()

        Spacer(modifier = Modifier.height(16.dp))

        VirtualBenefitsSection()

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF7B4DFF)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Get Card Only ₦199",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        VirtualFooterText()

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun VirtualCardShowcase() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Enjoy Online Payments",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.virtual_verve_card2),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun VirtualBenefitsSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFFF3EEDC),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = DividerDefaults.Thickness,
                color = Color(0xFFB39DDB)
            )
            Text(
                text = "Enjoy More Benefits",
                color = Color(0xFF6A3DE8),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = DividerDefaults.Thickness,
                color = Color(0xFFB39DDB)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BenefitItem("Instant Access", "Apply and activate instantly")

        Spacer(modifier = Modifier.height(12.dp))

        BenefitMerchantItem()

        Spacer(modifier = Modifier.height(12.dp))

        BenefitItem("Self-Managed Transactions", "Unique Merchant Control")

        Spacer(modifier = Modifier.height(12.dp))

        BenefitItem("Instant Refunds", "On Failed Transactions")

        Spacer(modifier = Modifier.height(12.dp))

        BenefitItem("Safety", "No physical handling, No risk of loss")
    }
}

@Composable
fun BenefitItem(title: String, desc: String) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = Color(0xFF6A3DE8),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = desc,
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun BenefitMerchantItem() {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "40,000+ Online Merchants",
                color = Color(0xFF6A3DE8),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MerchantTag("SportyBet")
                MerchantTag("NETFLIX")
                MerchantTag("Konga")
                MerchantTag("Showmax")
            }
        }
    }
}

@Composable
fun MerchantTag(name: String) {
    // Set colors based on merchant
    val (bgColor, textColor) = when (name.lowercase()) {
        "sportybet" -> Color.Red to Color.White
        "netflix" -> Color.White to Color.Red
        "konga" -> Color(0xFFFF4D6D) to Color.White
        "showmax" -> Color.White to Color(0xFFFF6D00)
        else -> Color.LightGray to Color.Black
    }

    Surface(
        shape = RoundedCornerShape(6.dp),
        color = bgColor
    ) {
        Text(
            text = name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
@Composable
fun VirtualFooterText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center, // center everything
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Licensed by CBN",
            color = Color.Black.copy(alpha = 0.6f),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "|",
            color = Color.Black.copy(alpha = 0.4f),
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Deposits Insured by NDIC",
            color = Color(0xFF7B4DFF),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
