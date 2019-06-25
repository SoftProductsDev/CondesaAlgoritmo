package condeso;


import com.google.gson.annotations.SerializedName;

public enum Contrato {
  @SerializedName("0")
  MiniJob,
  @SerializedName("1")
  Fijo,
  @SerializedName("2")
  otros
}
