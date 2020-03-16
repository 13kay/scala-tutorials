package com.baeldung.scala.forcomprehension

import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class ForLoopImplementation extends ForLoop {
  var sideEffectResult: Seq[Any] = Nil
  override def sideEffectFunction (arg: Any): Unit = {
    sideEffectResult = sideEffectResult :+ arg
  }

  def clean(): Unit = {
    sideEffectResult = Nil;
  }
}

class ForLoopTest {
  val forLoopTest = new ForLoopImplementation
  val rangeTo: Range = 1 to 3
  val rangeUntil: Range = 1 until 3
  val colors = Seq("R", "G", "B")
  val map = Map("R" -> "Red", "G" -> "Green", "B" -> "Blue")
  val deck = Map("♣" -> List("A", "K", "Q"),
                 "♦" -> List("J", "10"),
                 "♥" -> List("9", "8", "7"),
                 "♠" -> List("A", "K", "J", "6"))
  val numbers = List(1, 2, 3)
  val someIntValue: Option[Int] = Some(10)
  val someStringValue: Option[String] = Some("Ten")
  
  @Before
  def init(): Unit = {
    forLoopTest.clean()
  }

  @Test
  def iterateRangeToTest (): Unit = {
    forLoopTest.iterateRangeTo(rangeTo)
    val expected = Seq(1, 2, 3)
    assertEquals(expected, forLoopTest.sideEffectResult)
  }

  @Test
  def iterateRangeUntilTest (): Unit = {
    forLoopTest.iterateRangeUntil(rangeUntil)
    val expected = Seq(1, 2)
    assertEquals(expected, forLoopTest.sideEffectResult)
  }

  @Test
  def multipleGeneratorsTest (): Unit = {
    forLoopTest.multipleGenerators(rangeTo, rangeUntil)
    val expected = Seq("1, 1", "1, 2", "2, 1", "2, 2", "3, 1", "3, 2")
    assertEquals(expected, forLoopTest.sideEffectResult)
  }

  @Test
  def iterateCollectionTest (): Unit = {
    forLoopTest.iterateCollection(colors)
    val expected = Seq("R", "G", "B")
    assertEquals(expected, forLoopTest.sideEffectResult)
  }

  @Test
  def iterateCollectionWithMultipleGeneratorsTest (): Unit = {
    forLoopTest.iterateCollectionWithMultipleGenerators(colors)
    val expected = List("RRR ", "RRG ", "RRB ", "RGR ", "RGG ", "RGB ", "RBR ", "RBG ", "RBB ", "GRR ", "GRG ", "GRB ", "GGR ", "GGG ", "GGB ", "GBR ", "GBG ", "GBB ", "BRR ", "BRG ", "BRB ", "BGR ", "BGG ", "BGB ", "BBR ", "BBG ", "BBB ")
    assertEquals(expected, forLoopTest.sideEffectResult)
  }

  @Test
  def iterateCollectionsWithGuardsTest (): Unit = {
    forLoopTest.iterateCollectionsWithGuards(colors)
    val expected = List("RGB ", "RBG ", "GRB ", "GBR ", "BRG ", "BGR ")
    assertEquals(expected, forLoopTest.sideEffectResult)
  }

  @Test
  def iterateMapTest (): Unit = {
    forLoopTest.iterateMap(map)
    val expected = List("R is for Red", "G is for Green", "B is for Blue")
    assertEquals(expected, forLoopTest.sideEffectResult)
  }

  @Test
  def iterateMapMultipleGeneratorsTest (): Unit = {
    forLoopTest.iterateMapMultipleGenerators(deck)
    val expected = List("A of ♣", "K of ♣", "Q of ♣", "J of ♦", "10 of ♦", "9 of ♥", "8 of ♥", "7 of ♥", "A of ♠", "K of ♠", "J of ♠", "6 of ♠")
    assertEquals(expected, forLoopTest.sideEffectResult)
  }

  @Test
  def pureIterationTest (): Unit = {
    val result = forLoopTest.pureIteration(numbers)
    val expected = List("1 + 1 = 2", "2 + 2 = 4", "3 + 3 = 6")
    assertEquals(expected, result)
  }

  @Test
  def forComprehensionWithOptionalsTest (): Unit = {

    val resultFor = forLoopTest.forComprehensionWithOptionals(someIntValue, someStringValue)
    val resultMap = forLoopTest.mapOptionals(someIntValue, someStringValue)
    val expected = Some("10 is Ten")
    assertEquals(expected, resultFor)
    assertEquals(expected, resultMap)
    assertEquals(resultFor, resultMap)
  }
}