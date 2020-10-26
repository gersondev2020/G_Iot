package com.gautomation.giot.Class;

public class UnidadesAut {
        public String Valor_padrao_unidade(String tag){
        if( tag.contains("PT") ||  tag.contains("KG")){
            return "Kgf/m²";
        }else if(tag.contains("TT") || tag.contains("TEMPER")){
            return "°C";
        }else if(tag.contains("FT") || tag.contains("VZ") || tag.contains("VAZAO")){
            return "m³/h";
        }else if(tag.contains("RPM")){
            return "RPM";
        }else if(tag.contains("PH") || tag.contains("NIVEL") || tag.contains("LT")){
            return "%";
        }else if(tag.contains("POT") || tag.contains("EXPO")){
            return "KW";
        }else{
            return "Kgf/m²";
        }

    }
}
