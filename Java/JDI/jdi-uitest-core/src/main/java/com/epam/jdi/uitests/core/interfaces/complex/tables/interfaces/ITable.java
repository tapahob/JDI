package com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces;
/*
 * Copyright 2004-2016 EPAM Systems
 *
 * This file is part of JDI project.
 *
 * JDI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JDI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JDI. If not, see <http://www.gnu.org/licenses/>.
 */


import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import io.qameta.allure.Step;

import java.util.List;

/**
 * Created by roman.i on 20.10.2014.
 */

public interface ITable extends IText {
    /**
     * Get Cell by column/row index (Int) or name(String)
     */
    @Step
    ICell cell(Column column, Row row);

    @Step
    default ICell cell(String columnName, String rowName) {
        return cell(Column.column(columnName), Row.row(rowName));
    }

    @Step
    default ICell cell(int columnIndex, int rowIndex) {
        if (columnIndex <= 0 || rowIndex <= 0)
            throw new RuntimeException("Table indexes starts from 1");
        return cell(Column.column(columnIndex), Row.row(rowIndex));
    }

    @Step
    default ICell cell(int columnIndex, String rowName) {
        if (columnIndex <= 0)
            throw new RuntimeException("Table indexes starts from 1");
        return cell(Column.column(columnIndex), Row.row(rowName));
    }

    @Step
    default ICell cell(String columnName, int rowIndex) {
        if (rowIndex <= 0)
            throw new RuntimeException("Table indexes starts from 1");
        return cell(Column.column(columnName), Row.row(rowIndex));
    }
    /**
     * Get all Cells with values equals to searched value
     */
    @Step
    List<ICell> cells(String value);

    /**
     * Get first Cell in row with value contains expected value
     */
    @Step
    ICell cellContains(String value, Row row);

    /**
     * Get first Cell in row with value match expected regEx
     */
    @Step
    ICell cellMatch(String regex, Row row);

    /**
     * Get first Cell in column with value contains expected value
     */
    @Step
    ICell cellContains(String value, Column column);

    /**
     * Get first Cell in column with value match expected regEx
     */
    @Step
    ICell cellMatch(String regex, Column column);
    /**
     * Get all Cells with values contains expected value
     */
    @Step
    List<ICell> cellsContains(String value);

    /**
     * Get all Cells with values matches to searched regex
     */
    @Step
    List<ICell> cellsMatch(String regex);

    /**
     * Get first Cell with equals to searched value
     */
    @Step
    ICell cell(String value);

    /**
     * Get first Cell with matches to searched regex
     */
    @Step
    ICell cellMatch(String regex);

    /**
     * Searches Rows in table matches specified criteria colNameValues - list of search criteria in format columnName=columnValue<br>
     * = - Equals
     * ~= - Contains
     * *= - Match RegEx
     * e.g. rows("Name=Roman", "Profession=QA") <br>
     * e.g. rows("Name*=.* +*", "Profession~=Test") <br>
     * Each Row is map: columnName:cell
     */
    @Step
    MapArray<String, MapArray<String, ICell>> rows(String... colNameValues);

    MapArray<String, MapArray<String, ICell>> rows(String value, Column column);
    MapArray<String, MapArray<String, ICell>> rowsContains(String value, Column column);
    MapArray<String, MapArray<String, ICell>> rowsMatches(String regEx, Column column);
    /**
     * Searches Columns in table matches specified criteria rowNameValues - list of search criteria in format rowName=rowValue<br>
     * = - Equals
     * ~= - Contains
     * *= - Match RegEx
     * e.g. columns("Total=100", "Count=10") <br>
     * e.g. columns("Total*=\\d+", "Profession~=QA") <br>
     * Each Column is map: rowName:cell
     */
    @Step
    MapArray<String, MapArray<String, ICell>> columns(String... rowNameValues);

    MapArray<String, MapArray<String, ICell>> columns(String value, Row row);
    MapArray<String, MapArray<String, ICell>> columnsContains(String value, Row row);
    MapArray<String, MapArray<String, ICell>> columnsMatches(String regEx, Row row);

    /**
     * Waits while value appear in Row <br>
     * e.g. waitValue("100", row("Total")) <br>
     * or   waitValue("100", row(5))
     */
    @Step
    boolean waitValue(String value, Row row);

    /**
     * Waits while value appear in Column <br>
     * e.g. waitValue("Roman", column("Name")) <br>
     * or   waitValue("Roman", column(3))
     */
    @Step
    boolean waitValue(String value, Column column);

    /**
     * Indicates are any rows in table. Check immediately
     */
    @Step
    boolean isEmpty();

    /**
     * Wait while at least one row appear in table
     */
    @Step
    boolean waitHasRows();

    /**
     * Wait while at least count of rows appear in table
     */
    @Step
    boolean waitRows(int count);

    /**
     * Get first Cell with searched value in row by index (Int) or name(String)<br>
     * e.g. cell("100", row("Total")) <br>
     * or   cell("100", row(5))
     */
    @Step
    ICell cell(String value, Row row);

    /**
     * Get first Cell with searched value in column by index (Int) or name(String)<br>
     * e.g. cell("Roman", column("Name")) <br>
     * or   cell("Roman", column(3))
     */
    // TODO Cell search with predicates
    @Step
    ICell cell(String value, Column column);

    /**
     * Get all Cells with values matches to searched in Row by index (Int) or name(String) <br>
     * e.g. cellsMatch(".*uccess.*", row("Result")) <br>
     * or   cellsMatch(".*uccess.*", row(5))
     */
    @Step
    List<ICell> cellsMatch(String regex, Row row);

    /**
     * Get all Cells with values matches to searched in Column by index (Int) or name(String) <br>
     * e.g. cellsMatch("Roma.*", column("Name")) <br>
     * or   cellsMatch("Roma.*", column(3))
     */
    @Step
    List<ICell> cellsMatch(String regex, Column column);

    /**
     * Get Row cells for Cell with searched value in Column by index(Int) or name(String) <br>
     * e.g. row("Roman", column("Name")) <br>
     * or   row("Roman", column(3)) <br>
     * Each Row is map: columnName:cell
     */
    @Step
    MapArray<String, ICell> row(String value, Column column);

    /**
     * Get Column cells for Cell with searched value in Row by index(Int) or name(String) <br>
     * e.g. column("100", row("Total") <br>
     * or   column("100", row(5)) <br>
     * Each Column is map: rowName:cell
     */
    @Step
    MapArray<String, ICell> column(String value, Row row);
    /**
     * Get Column cells for Cell with searched value contains in Row's values by index(Int) or name(String) <br>
     * e.g. columnContains("Framewo", row("Total") <br>
     * or   columnContains("mwork", row(5)) <br>
     * Each Column is map: rowName:cell
     */
    @Step
    MapArray<String, ICell> columnContains(String value, Row row);
    /**
     * Get Column cells for Cell with Row's values match regEx by index(Int) or name(String) <br>
     * e.g. columnContains(".*work", row("Total") <br>
     * or   columnContains("Frame.+ork", row(5)) <br>
     * Each Column is map: rowName:cell
     */
    @Step
    MapArray<String, ICell> columnMatch(String regEx, Row row);
    /**
     * Get Column cells for Cell with searched value contains in Column's values by index(Int) or name(String) <br>
     * e.g. row("Framewo", column("Name")) <br>
     * or   row("mwork", column(3)) <br>
     * Each Row is map: columnName:cell
     */
    @Step
    MapArray<String, ICell> rowContains(String value, Column column);
    /**
     * Get Column cells for Cell with Column's values match regEx by index(Int) or name(String) <br>
     * e.g. row(".*work", column("Name")) <br>
     * or   row("Frame.+ork", column(3)) <br>
     * Each Row is map: columnName:cell
     */
    @Step
    MapArray<String, ICell> rowMatch(String regEx, Column column);

    IRow rows();

    /**
     * Get Row with index <br>
     * Each Row is map: columnName:cell
     */
    @Step
    MapArray<String, ICell> row(int rowNum);

    /**
     * Get Row with name <br>
     * Each Row is map: columnName:cell
     */
    @Step
    MapArray<String, ICell> row(String rowName);

    /**
     * Get Row value
     */
    @Step
    List<String> rowValue(int colNum);

    /**
     * Get Row value
     */
    @Step
    List<String> rowValue(String colName);

    IColumn columns();

    /**
     * Get Column with index <br>
     * Each Column is map: rowName:cell
     */
    @Step
    MapArray<String, ICell> column(int colNum);

    /**
     * Get Column with name <br>
     * Each Column is map: rowName:cell
     */
    @Step
    MapArray<String, ICell> column(String colName);

    /**
     * Get Column value
     */
    @Step
    List<String> columnValue(int colNum);

    /**
     * Get Column value
     */
    @Step
    List<String> columnValue(String colName);

    /**
     * Get Header
     */
    @Step
    MapArray<String, ISelect> header();

    @Step
    ISelect header(String name);

    /**
     * Get Header
     */
    @Step
    List<String> headers();

    /**
     * Get Footer
     */
    @Step
    List<String> footer();

    /**
     * Get All Cells
     */
    @Step
    List<ICell> getCells();

    /**
     * Clean all already found Cells
     */
    void clean();

    /**
     * Similar to clean
     */
    void clear();

    ITable useCache(boolean value);
    default ITable useCache() { return useCache(true); }

    ITable clone();

    ITable copy();

    ITable hasAllHeaders();

    ITable hasNoHeaders();

    ITable hasOnlyColumnHeaders();

    ITable hasOnlyRowHeaders();

    ITable hasColumnHeaders(List<String> value);

    <THeaders extends Enum> ITable hasColumnHeaders(Class<THeaders> headers);

    ITable hasRowHeaders(List<String> value);

    <THeaders extends Enum> ITable hasRowHeaders(Class<THeaders> headers);

    ITable setColumnsCount(int value);

    ITable setRowsCount(int value);
}