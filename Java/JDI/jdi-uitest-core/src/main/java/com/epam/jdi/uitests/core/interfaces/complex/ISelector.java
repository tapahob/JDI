package com.epam.jdi.uitests.core.interfaces.complex;
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

import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.base.ISetValue;
import io.qameta.allure.Step;

import java.util.List;

import static com.epam.commons.PrintUtils.print;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public interface ISelector<TEnum extends Enum> extends IBaseElement, ISetValue {
    /**
     * @param name Specify name using string
     *             Select Element with name (use text) from list
     */
    @Step
    void select(String name);

    /**
     * @param name Specify name using enum
     *             Select Element with name (use enum) from list
     */
    @Step
    void select(TEnum name);

    /**
     * @param index Specify digit to select
     *              Select Element with name (use index) from list
     */
    @Step
    void select(int index);

    /**
     * @return Get name of the selected Element
     */
    @Step
    String getSelected();

    /**
     * @return Get index of the selected Element
     */
    @Step
    int getSelectedIndex();

    /**
     * @param name Specify name using string
     * @return Is option selected?
     */
    @Step
    boolean isSelected(String name);

    /**
     * @param name Specify name using enum
     * @return Is option selected?
     */
    @Step
    boolean isSelected(TEnum name);

    /**
     * @param name Specify name using string
     * Wait while option (from text) is selected. Return false if this not happens
     */
    @Step
    void waitSelected(String name);

    /**
     * @param name Specify name using enum
     * Wait while option (from enum) is selected. Return false if this not happens
     */
    @Step
    void waitSelected(TEnum name);
/*
    boolean isDisplayed(String name);
    boolean isDisplayed(int index);*/
    /**
     * @return Get labels of all options
     */
    @Step
    List<String> getOptions();

    default List<String> getNames() {
        return getOptions();
    }

    default List<String> getValues() {
        return getOptions();
    }

    /**
     * @return Get all options labels in one string separated with “; ”
     */
    @Step
    default String getOptionsAsText() {
        return print(getOptions());
    }
}