/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Domain.DTO.DnaDB;
import Domain.Models.*;
import Infraestructure.Data.DnaDataHandler;
import Utilities.Exceptions.DnaExceptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JONATHANBERNAL
 */
public class DnaValidateApplicationImpl {

    private int n;
    private List<Points> pointCheck;
    private int SecuenceCount;
    private DnaSecuence dna;

    public boolean validateDna(DnaSecuence dnaInput) throws DnaExceptions {

        int id = dnaInput.getDna().hashCode() & 0x7fffffff;
        DnaDB dnaDB = DnaDataHandler.getDnaSecuence(id);
        if (Objects.isNull(dnaDB)) {
            return validateDnaIfNotExist(dnaInput,id);
        } else {
            return dnaDB.getIsMutant();
        }
    }

    public int validatePositionMove(int x, int y, int movx, int movy) {
        if ((x + movx >= 0 && x + movx < n) && (y + movy >= 0 && y + movy < n)) {
            if (dna.getDna().get(x).charAt(y) == dna.getDna().get(x + movx).charAt(y + movy)) {
                return 1 + validatePositionMove(x + movx, y + movy, movx, movy);
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    private void ValidatePoint(int x, int y, int movx, int movy) {
        if (!(pointCheck.indexOf(new Points(x, y)) >= 0)) {
            int validate = (int) (validatePositionMove(x, y, movx, movy) / 4);
            if (validate > 0) {
                for (int j = 1; j <= validate * 4; j++) {
                    pointCheck.add(new Points(x + (j * movx), y + (j * movy)));
                }
            }

            SecuenceCount = SecuenceCount + validate;
        }
    }

    public boolean validateNitrogenBase(DnaSecuence dnaInput) throws DnaExceptions {
        String regex = "^[A,T,C,G]*$";
        Pattern p = Pattern.compile(regex);
        for (String line : dnaInput.getDna()) {
            if (line.length() == n) {
                Matcher m = p.matcher(line);
                if (!m.matches()) {
                    throw new DnaExceptions("Bad Request: Incorrect DNA sequence, nitrogenous bases can only be (A, T, C, G)", 400);
                }
            } else {
                throw new DnaExceptions("Bad Request: DNA sequence is not nxn size", 400);
            }
        }
        return true;
    }

    public boolean validateDnaIfNotExist(DnaSecuence dnaInput, int id) throws DnaExceptions {
            DnaDB dnaDB = new DnaDB();
            dnaDB.setDna(dnaInput.getDna());
            dnaDB.setId(id);
            this.dna = dnaInput;
            n = dna.getDna().size();
            validateNitrogenBase(dnaInput);
            pointCheck = new ArrayList<Points>();
            int x = 0;
            int y = 0;
            SecuenceCount = 0;
            for (x = 0; x < n; x++) {

                for (y = 0; y < n; y++) {
                    //Valida posición x-1 y y-1
                    ValidatePoint(x, y, -1, -1);
                    //Valida posición x-1 y y
                    ValidatePoint(x, y, -1, 0);
                    //Valida posición x-1 y y+1
                    ValidatePoint(x, y, -1, 1);
                    //Valida posición x y y-1
                    ValidatePoint(x, y, 0, -1);
                    //Valida posición x y y+1
                    ValidatePoint(x, y, 0, 1);
                    //Valida posición x+1 y y-1
                    ValidatePoint(x, y, 1, -1);
                    //Valida posición x+1 y y
                    ValidatePoint(x, y, 1, 0);
                    //Valida posición x-1 y y-1
                    ValidatePoint(x, y, +1, +1);

                    if (SecuenceCount > 1) {
                        dnaDB.setIsMutant(true);
                        DnaDataHandler.addDNA(dnaDB);
                        return true;
                    }
                }

            }
            dnaDB.setIsMutant(false);
            DnaDataHandler.addDNA(dnaDB);
            return false;
    }

    public MutantReport getReport(){
        return DnaDataHandler.getReport();
    }
}
