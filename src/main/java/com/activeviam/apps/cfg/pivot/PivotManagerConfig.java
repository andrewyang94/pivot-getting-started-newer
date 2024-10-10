/*
 * Copyright (C) ActiveViam 2024
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam Limited. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */
package com.activeviam.apps.cfg.pivot;

import org.springframework.context.annotation.Configuration;

import com.activeviam.activepivot.core.datastore.api.builder.StartBuilding;
import com.activeviam.activepivot.core.intf.api.description.IActivePivotManagerDescription;
import com.activeviam.activepivot.server.spring.api.config.IActivePivotManagerDescriptionConfig;
import com.activeviam.apps.cfg.DatastoreSelectionConfig;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class PivotManagerConfig implements IActivePivotManagerDescriptionConfig {
    /* *********************/
    /* OLAP Property names */
    /* *********************/

    public static final String MANAGER_NAME = "Manager";
    public static final String CATALOG_NAME = "Catalog";
    public static final String SCHEMA_NAME = "Schema";

    /* ********** */
    /* Formatters */
    /* ********** */
    public static final String DOUBLE_FORMATTER = "DOUBLE[#,###.##]";
    public static final String INT_FORMATTER = "INT[#,###]";
    public static final String TIMESTAMP_FORMATTER = "DATE[HH:mm:ss]";

    public static final String NATIVE_MEASURES = "Native Measures";

    private final DatastoreSelectionConfig datastoreSelectionConfig;
    private final CubeConfig cubeConfig;

    @Override
    public IActivePivotManagerDescription managerDescription() {
        return StartBuilding.managerDescription(MANAGER_NAME)
                .withCatalog(CATALOG_NAME)
                .containingAllCubes()
                .withSchema(SCHEMA_NAME)
                .withSelection(datastoreSelectionConfig.createSchemaSelectionDescription())
                .withCube(cubeConfig.createCubeDescription())
                .build();
    }
}
