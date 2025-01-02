package org.example.pigeon.base

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SetupAppTest::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
annotation class PigeonTest
