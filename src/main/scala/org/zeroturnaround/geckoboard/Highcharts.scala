package org.zeroturnaround.geckoboard

object Highcharts {
  type SeriesType = String
  val Line: SeriesType = "line"
  val Bar: SeriesType = "bar"
  val Spline: SeriesType = "spline"
  val Column: SeriesType = "column"

  type PlotOptions = Map[SeriesType, SeriesTypePlotOptions]

  implicit def axis2list(axis: Axis) = List(axis)
}

import Highcharts._

case class HighchartsChart(
  chart: Chart,
  title: Title,
  subtitle: Option[Title] = None,
  xAxis: List[Axis],
  yAxis: List[Axis],
  plotOptions: Option[PlotOptions] = None,
  series: List[Series[_]]
)

case class Chart(renderTo: String, defaultSeriesType: SeriesType)
case class Title(text: String)

trait Axis
case class AxisCategories(categories: List[String]) extends Axis
case class AxisTitle[A](title: Title, opposite: Boolean = false, reversed: Boolean = false, min: Option[A] = None, max: Option[A] = None) extends Axis

case class Series[A](
  name: String,
  data: List[A],
  `type`: Option[SeriesType] = None,
  xAxis: Int = 0,
  yAxis: Int = 0
)

case class SeriesTypePlotOptions(
  dataLabels: Option[DataLabelsOptions] = None,
  enableMouseTracking: Boolean = true
)

case class DataLabelsOptions(enabled: Boolean)
