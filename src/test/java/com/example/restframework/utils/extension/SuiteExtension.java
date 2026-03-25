package com.example.restframework.utils.extension;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public interface SuiteExtension extends BeforeAllCallback {
    default void beforeSuite(ExtensionContext extensionContext) {
    }

    default void afterSuite(ExtensionContext extensionContext) {
    }

    @Override
    default void beforeAll(ExtensionContext context) throws Exception {
        context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL)
                .computeIfAbsent(
                        SuiteExtension.class,
                        suiteExtensionClass -> {
                            beforeSuite(context);
                            return (AutoCloseable) () -> afterSuite(context);
                        });
    }
}
