/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.mytitlefx;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class JavscazUtil {
    
        private static String opertativeSystem = System.getProperty("os.name").toLowerCase();
      public static Date getFechaHoraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        Date date2 = Date.from(ahora.atZone(ZoneId.systemDefault()).toInstant());
        return date2;
    }
      
       // <editor-fold defaultstate="collapsed" desc="boolean isLinux()">
    public static boolean isLinux() {

        return (opertativeSystem.indexOf("nix") >= 0 || opertativeSystem.indexOf("nux") >= 0 || opertativeSystem.indexOf("aix") > 0);

    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="boolean isSolaris()">

    public static boolean isSolaris() {

        return (opertativeSystem.indexOf("sunos") >= 0);

    }
    // </editor-fold>
    
     // <editor-fold defaultstate="collapsed" desc="isWindows()">
    /*
    Implementado desde el ejemplo de Mkyong
    https://mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
     */
    public static boolean isWindows() {

        return (opertativeSystem.indexOf("win") >= 0);

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="isMac()">
    public static boolean isMac() {

        return (opertativeSystem.indexOf("mac") >= 0);

    }
    // </editor-fold>
    
    
     // <editor-fold defaultstate="collapsed" desc="List<String> buildCommandPing(String ipAddress)">
      public static List<String> buildCommandPing(String ipAddress) {
          System.out.println("....Llego a buildCommandPing");
   List<String> command = new ArrayList<>();
   command.add("ping");
          try {
                if (JavscazUtil.isWindows()) {
            command.add("-n");
        } else {
            if (JavscazUtil.isMac() || JavscazUtil.isLinux() || JavscazUtil.isSolaris()) {
                command.add("-c");
            } else {
                throw new UnsupportedOperationException("Unsupported operating system");
            }
        }

        command.add("1");
        command.add(ipAddress);
          } catch (Exception e) {
              System.out.println("buildCommandPing() "+e.getLocalizedMessage());
          }
     
        

      

        return command;
    }    // </editor-fold>
}
