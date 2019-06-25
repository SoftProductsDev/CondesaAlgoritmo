package horario;

import com.google.gson.annotations.SerializedName;

public enum ShiftType {
  @SerializedName("0")
  GM,
  @SerializedName("1")
  G,
  @SerializedName("2")
  F,
  @SerializedName("3")
  H,
  @SerializedName("4")
  B,
  @SerializedName("5")
  R,
  @SerializedName("6")
  E
}
