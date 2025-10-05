package com.myapp.ui.feature.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.myapp.model.AppIcon
import com.myapp.ui.component.BaseScreen
import com.myapp.ui.component.noRippleClickable
import com.myapp.ui.feature.product.ProductListScreen
import com.myapp.util.rememberBitmapResource
import org.koin.compose.koinInject

/**
 * Home screen
 *
 * @constructor Create empty Home screen
 */
class HomeScreen : BaseScreen<HomeScreenViewModel>() {

    @Composable
    override fun getViewModel(): HomeScreenViewModel = koinInject()

    @Composable
    override fun contentView() {
        val navigator = LocalNavigator.currentOrThrow
        // Fix navigation to MainScreen
        HomeScreenWithCards(homeItemList(), onAppClick = {
            navigator.push(ProductListScreen())
        })
    }
}


@Composable
fun HomeScreenWithCards(
    appIcons: List<AppIcon>,
    onAppClick: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(60.dp), // padding around the container
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth()
                .background(Color.Red).fillMaxHeight(),
            //  shape = RoundedCornerShape(40.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = Modifier.padding(40.dp),
                    horizontalArrangement = Arrangement.spacedBy(40.dp),
                    verticalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    items(appIcons) { app ->
                        Card(
                            modifier = Modifier
                                .size(90.dp)
                                .noRippleClickable { onAppClick(app.id) },
                            shape = RoundedCornerShape(24.dp),
                            elevation = 8.dp,
                            backgroundColor = Color.White,
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = app.painter,
                                    contentDescription = app.contentDescription,
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun homeItemList(): MutableList<AppIcon> {
    return mutableListOf(
        AppIcon("powerbi.png", rememberBitmapResource("drawables/passcode.png"), "Power BI"),
        AppIcon("netlify.png", rememberBitmapResource("drawables/logo.png"), "Netlify"),
        AppIcon("cloud-upload.png", rememberBitmapResource("drawables/logo.png"), "Cloud Upload"),
        AppIcon("sap.png", rememberBitmapResource("drawables/logo.png"), "SAP"),
        AppIcon("adobe.png", rememberBitmapResource("drawables/logo.png"), "Adobe"),
        AppIcon("salesforce.png", rememberBitmapResource("drawables/logo.png"), "Salesforce"),
        AppIcon("wpp.png", rememberBitmapResource("drawables/logo.png"), "WPP"),
        AppIcon("slack.png", rememberBitmapResource("drawables/logo.png"), "Slack"),
        AppIcon("segment.png", rememberBitmapResource("drawables/logo.png"), "Segment"),
        AppIcon("shopify.png", rememberBitmapResource("drawables/logo.png"), "Shopify"),
        AppIcon("delta.png", rememberBitmapResource("drawables/logo.png"), "Delta"),
        AppIcon("cube.png", rememberBitmapResource("drawables/logo.png"), "Cube"),
        AppIcon("graphql.png", rememberBitmapResource("drawables/logo.png"), "GraphQL"),
        AppIcon("gitlab.png", rememberBitmapResource("drawables/logo.png"), "GitLab"),
        AppIcon("bigcommerce.png", rememberBitmapResource("drawables/logo.png"), "BigCommerce"),
    )
}
