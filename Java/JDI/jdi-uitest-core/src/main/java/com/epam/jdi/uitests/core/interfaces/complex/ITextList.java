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
import com.epam.jdi.uitests.core.interfaces.base.IHasValue;
import com.epam.jdi.uitests.core.interfaces.base.IVisible;
import io.qameta.allure.Step;

import java.util.List;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public interface ITextList<TEnum extends Enum> extends IBaseElement, IHasValue, IVisible {
    /**
     * @param name Specify string by String mechanic
     * @return Get textList’s text by specified param
     */
    @Step
    String getText(String name);

    /**
     * @param index Specify string by Integer mechanic
     * @return Get textList’s text by specified param
     */
    @Step
    String getText(int index);

    /**
     * @param enumName Specify string by Enum mechanic
     * @return Get textList’s text by specified param
     */
    @Step
    String getText(TEnum enumName);

    /**
     * @return Returns strings count
     */
    @Step
    int count();

    /**
     * @return Wait while TextList’s text contains expected text. Returns Element’s text
     */
    @Step
    List<String> waitText(String str);

    /**
     * @return Return list of strings of TextList
     */
    @Step
    List<String> getTextList();

    /**
     * @return Return first String in list
     */
    @Step
    String getFirstText();

    /**
     * @return Return last String in list
     */
    @Step
    String getLastText();
}