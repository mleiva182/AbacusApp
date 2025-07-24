import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mleiva.abacusapp.ui.portfolio.PerformanceChart
import com.mleiva.abacusapp.ui.portfolio.PortfolioViewModel
import com.mleiva.abacusapp.utils.Utils

@Composable
fun PortfolioScreen(onSearchClick: () -> Unit) {
    val viewModel = remember { PortfolioViewModel() }

    val value = viewModel.portfolioValue
    val returns = viewModel.returns
    val cashBalance = viewModel.cashBalance
    val performance = viewModel.performance
    val positions = viewModel.positions
    val error = viewModel.error

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        if (error != null) {
            Text("Error: $error", color = Color.Red)
        }

        Text(
            text = Utils.formatCurrency(value?.toDouble() ?: 0.0, "USD"),
            fontSize = 28.sp, fontWeight = FontWeight.Bold
        )

        Text(
            "Rendimiento: ${Utils.formatCurrency(returns?.return_percentage?.toDouble() ?: 0.0, "USD")}%",
            style = MaterialTheme.typography.headlineSmall, color = Color.Green)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Gráfico (YTD)", fontWeight = FontWeight.Bold)
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(Color.LightGray)) {
            PerformanceChart(performanceData = performance)
        }


        Spacer(modifier = Modifier.height(16.dp))

        Text("Poder de compra", fontWeight = FontWeight.Bold)
        Row {
            Column(modifier = Modifier.weight(1f)) {
                Text("USD")
                Text("$${cashBalance.value?.USD ?: "---"}")
            }
            Column(modifier = Modifier.weight(1f)) {
                Text("CLP")
                Text("$${cashBalance.value?.CLP ?: "---"}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Posiciones", fontWeight = FontWeight.Bold)

        LazyColumn {
            items(positions) { position ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(position.name)
                        Text(position.ticker, fontSize = 12.sp, color = Color.Gray)
                    }
                    Text("${Utils.formatCurrency(position.value?.toDouble() ?: 0.0, "USD")}")
                }
            }
        }
    }
}
