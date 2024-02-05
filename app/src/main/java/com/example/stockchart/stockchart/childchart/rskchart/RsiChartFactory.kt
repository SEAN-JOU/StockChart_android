package com.example.stockchart.childchart.rskchart

import com.example.stockchart.IStockChart
import com.example.stockchart.childchart.base.AbsChildChartFactory


/**
 * @author wangyiqian E-mail: wangyiqian9891@gmail.com
 * @version 创建时间: 2023/3/9
 */
class RsiChartFactory(stockChart: IStockChart, childChartConfig: RsiChartConfig) :
    AbsChildChartFactory<RsiChartConfig>(stockChart, childChartConfig) {
    override fun createChart() = RsiChart(stockChart, childChartConfig)
}