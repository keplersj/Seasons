package k2b6s9j.Seasons

import cpw.mods.fml.common.{TickType, ITickHandler, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.registry.TickRegistry
import cpw.mods.fml.relauncher.Side
import java.util
import k2b6s9j.Seasons.months.December
import net.minecraft.server.MinecraftServer

@Mod(name = "Seasons", modid = "seasons", version = "ModJam", modLanguage = "scala")
object Seasons {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) {

    Log.info("Seasons")
    Log.info("Copyright Kepler Sticka-Jones 2013")

  }

  @EventHandler
  def Init(event: FMLInitializationEvent) {

    TickRegistry.registerTickHandler(SeasonalTickHandler, Side.SERVER)

  }

}

object SeasonalTickHandler extends ITickHandler {

  override def tickStart(kind: util.EnumSet[TickType], data: AnyRef*) {
    val time: Long = MinecraftServer.getServer().worldServers(0).getTotalWorldTime
    TimeManager.findMonth(time.toInt)
  }

  override def tickEnd(kind: util.EnumSet[TickType], data: AnyRef*) {
    if (kind.equals(util.EnumSet.of(TickType.SERVER))) {
      December.setWeather()
    }
  }

  override def getLabel: String = {
    "Seasonal TickHandler"
  }

  override def ticks(): java.util.EnumSet[cpw.mods.fml.common.TickType] = {
    util.EnumSet.of(TickType.SERVER)
  }

}