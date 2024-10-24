/*
 * Copyright (C) ActiveViam 2024
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam Limited. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */
package com.activeviam.apps.cfg;

import static com.activeviam.database.api.types.ILiteralType.DOUBLE;
import static com.activeviam.database.api.types.ILiteralType.LOCAL_DATE;
import static com.activeviam.database.api.types.ILiteralType.STRING;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.springframework.context.annotation.Configuration;

import com.activeviam.activepivot.core.datastore.api.builder.StartBuilding;
import com.activeviam.activepivot.server.spring.api.config.IDatastoreSchemaDescriptionConfig;
import com.activeviam.apps.constants.StoreAndFieldConstants;
import com.activeviam.database.api.types.ILiteralType;
import com.activeviam.database.datastore.api.description.IDatastoreSchemaDescription;
import com.activeviam.database.datastore.api.description.IReferenceDescription;
import com.activeviam.database.datastore.api.description.IStoreDescription;
import com.activeviam.database.datastore.api.description.impl.DatastoreSchemaDescription;
import com.activeviam.database.datastore.api.description.impl.StoreDescription;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DatastoreSchemaConfig implements IDatastoreSchemaDescriptionConfig {

    private IStoreDescription createTradesStoreDescription() {
        return StoreDescription.builder()
                .withStoreName(StoreAndFieldConstants.TRADES_STORE_NAME)
                .withField(StoreAndFieldConstants.ASOFDATE, LOCAL_DATE)
                .asKeyField()
                .withField(StoreAndFieldConstants.TRADES_TRADE_ID, STRING)
                .asKeyField()
                .withField(StoreAndFieldConstants.TRADES_NOTIONAL, DOUBLE)
                .build();
    }

    private Collection<IReferenceDescription> references() {
        return Collections.emptyList();
    }

    private IStoreDescription createProductsStoreDescription() {
        return StartBuilding.store()
                .withStoreName("PRODUCTS")
                .withField("PRODUCTS_PRODUCT_ID", ILiteralType.INT)
                .asKeyField()
                .withField("PRODUCTS_PRODUCT_NAME", ILiteralType.STRING)
                .withField("PRODUCTS_PRODUCT_CATEGORY", ILiteralType.STRING)
                .withField("PRODUCTS_SUPPLIER", ILiteralType.STRING)
                .withField("PRODUCTS_PURCHASING_PRICE_PER_UNIT", DOUBLE)
                .build();
    }

    @Override
    public IDatastoreSchemaDescription datastoreSchemaDescription() {
        var stores = new LinkedList<IStoreDescription>();
        stores.add(createTradesStoreDescription());
        stores.add(createProductsStoreDescription());


        return new DatastoreSchemaDescription(stores, references());
    }
}
