package ar.edu.unahur.obj2.semillasAlViento

// Mutación controlada
// la altura de la planta está marcada como var, pero en
// este ejercicio la altura va a ser inmutable para cada planta
// lo cual deberia ser val altura: Float
abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) {
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  //Cohesion
  //Por que un método de parcela depende de la planta?
  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

// Simplicidad + Cohesion a nivel de clase, se podía separar en una clase
// SojaTransgénica
class Soja(anioObtencionSemilla: Int, altura: Float, val esTransgenica: Boolean) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    // Si se ivide las clase Soja en 2, la clase
    // soja transgenica el return sería horasDeSolQueTolera * 2
    return if (esTransgenica) horasBase * 2 else horasBase
  }

  // Cohesion a nivel de método
  override fun daSemillas(): Boolean  {
    if (this.esTransgenica) {
      return false
    }

    // Cohesion, no debería hacer tanta lógica en un return
    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}
