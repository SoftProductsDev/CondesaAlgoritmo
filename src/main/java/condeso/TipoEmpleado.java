package condeso;


import com.google.gson.annotations.SerializedName;

public enum TipoEmpleado {
  @SerializedName("0")
  GM,
  @SerializedName("1")
  Equipo,
  @SerializedName("2")
  Nuevo,
  @SerializedName("3")
  Encargado
}
