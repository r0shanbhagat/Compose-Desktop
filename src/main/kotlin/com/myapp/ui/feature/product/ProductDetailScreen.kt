package com.myapp.ui.feature.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.myapp.data.domain.model.ProductDetail
import com.myapp.ui.component.BaseScreen
import com.myapp.ui.component.ContentToolbarView
import com.myapp.ui.feature.product.state.StateEvent
import org.koin.compose.koinInject

class ProductDetailScreen(private val productId: String) : BaseScreen<ProductViewModel>() {

    @Composable
    override fun getViewModel(): ProductViewModel = koinInject()

    @Composable
    override fun contentView() {
        LaunchedEffect(Unit) {
            viewModel.setStateIntent(StateEvent.ProductDetails(productId))
        }
        ContentToolbarView(
            title = "Details",
            surfaceColor = MaterialTheme.colors.background,
            content = {
                val result = viewModel.product.value

                if (result.isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                }

                result.detail?.let {
                    Column(modifier = Modifier.fillMaxSize()) {
                        ListDetailItem(it)
                    }
                }

                if (result.error.isNotEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = result.error.toString())
                    }
                }
            })

    }

    @Composable
    fun ListDetailItem(category: ProductDetail) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                        .weight(0.4f),
                    painter = rememberAsyncImagePainter(category.image),
                    contentDescription = ""
                )
                UserDescription(category, Modifier.weight(0.6f))
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
        }
    }

    @Composable
    fun UserDescription(category: ProductDetail, modifier: Modifier) {
        Column(modifier = modifier) {
            Text(category.title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Text(
                category.description,
                fontSize = 12.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
