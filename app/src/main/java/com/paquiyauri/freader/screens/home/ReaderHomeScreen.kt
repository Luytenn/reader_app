package com.paquiyauri.freader.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.paquiyauri.freader.components.FABContent
import com.paquiyauri.freader.components.ReaderAppBar
import com.paquiyauri.freader.components.TitleSection
import com.paquiyauri.freader.model.MBook
import com.paquiyauri.freader.navigation.ReaderScreens

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Home(navController: NavController = NavController(LocalContext.current)){
    Scaffold(topBar = {
                      ReaderAppBar(title = "A.Reader", navController = navController)
    },
        floatingActionButton = {
            FABContent{

            }
        }) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it),) {
            //home content
            HomeContent(navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {

    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty())
        FirebaseAuth.getInstance().currentUser?.email?.split("@")
            ?.get(0)else
        "N/A"
    Column(Modifier.padding(2.dp),
           verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading \n " + " activity right now...")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colorScheme.secondary)
                Text(text = currentUserName!!,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip)
                Divider()
            }
        }

        ListCard()
    }
}


@Preview
@Composable
fun ListCard(book: MBook = MBook("asdf", "Running", "Me and you", "hello world"),
     onPressedDetails: (String) -> Unit = {} ) {

    val context = LocalContext.current
    val resources = context.resources

    val displayMetrics = resources.displayMetrics

    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp

    Card(shape = RoundedCornerShape(29.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable { onPressedDetails.invoke(book.title.toString()) }) {

        Column(modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start) {
            Row(horizontalArrangement = Arrangement.Center) {

                Image(painter = rememberImagePainter(data = ""),
                    contentDescription = "book image",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp)
                        .padding(4.dp))
                Spacer(modifier = Modifier.width(50.dp))

                Column(modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "Fav Icon",
                        modifier = Modifier.padding(bottom = 1.dp))

                    BookRating(score = 5.5)
                }

            }
            Text(text = book.title.toString(), modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)

            Text(text = book.authors.toString(), modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.labelSmall) }

        Row(horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom) {

            RoundedButton("Not Yet",
                radius = 70)

        }
    }


}

@Preview
@Composable
fun Card2() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
    ){

    }
}


@Composable
fun BookRating(score: Double = 4.5) {
    Surface(modifier = Modifier
        .height(70.dp)
        .padding(4.dp),
        shape = RoundedCornerShape(56.dp),
        tonalElevation = 0.dp,
        color = Color.White) {

        Column(modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            Icon(imageVector = Icons.Filled.StarBorder, contentDescription = "Start",
                modifier = Modifier.padding(3.dp))
            Text(text = score.toString(), style = MaterialTheme.typography.labelSmall)
        }


    }
}

@Preview
@Composable
fun RoundedButton(
    label: String = "Reading",
    radius: Int = 29,
    onPress: () -> Unit = {}) {
    Surface(modifier = Modifier.clip(RoundedCornerShape(
        bottomEndPercent = radius,
        topStartPercent = radius)),
        color = Color(0xFF92CBDF)) {

        Column(modifier = Modifier
            .width(90.dp)
            .heightIn(40.dp)
            .clickable { onPress.invoke() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = label, style = TextStyle(color = Color.White, fontSize = 15.sp ))

        }

    }
}


/*
@Composable
fun ReadingRighNowArea(books: List<MBook>, navController: NavController) {

}
*/


