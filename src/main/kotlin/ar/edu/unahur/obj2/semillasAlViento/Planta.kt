package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, val altura: Float) {
  fun esFuerte() = this.horasDeSolQueTolera() > 10


  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

open class Soja(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    return when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }
  }

  override fun daSemillas(): Boolean  {
    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}

class SojaTransgenica(anioObtencionSemilla: Int, altura: Float) : Soja(anioObtencionSemilla, altura) {

  override fun horasDeSolQueTolera(): Int {
    return super.horasDeSolQueTolera() * 2
  }
  override fun daSemillas(): Boolean {
    return false
  }
}
