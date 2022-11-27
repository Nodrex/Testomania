plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version ("1.7.0-1.0.6")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.earth.testomania"
    defaultConfig {
        applicationId = "com.earth.testomania"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = System.getenv("KEYSTORE_ALIAS")
            keyPassword = System.getenv("KEYSTORE_PRIVATE_KEY")
            storeFile = file("../keystore.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
        }
    }

    buildTypes {
        debug {
            extra.apply {
                set("enableCrashlytics", false)
                set("alwaysUpdateBuildId", false)
                set("enableCrashlytics", false)
            }
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packagingOptions {
        resources {
            excludes += ("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {
    implementation("androidx.fragment:fragment-ktx:1.5.1")
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.kiwi)
    implementation(libs.bundles.google)
    implementation(libs.coil)
    implementation(libs.bundles.firebase)
    implementation(libs.bundles.androidx.compose)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.moshi)
    implementation(libs.bundles.coroutines)

    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.android.test)
    debugImplementation(libs.bundles.debug.tools)

    kapt(libs.androidx.room.compiler)
    kapt(libs.moshi.codegen)
    kapt(libs.google.hilt.androidCompiler)
    kaptTest(libs.google.hilt.androidCompiler)
    kapt(libs.google.hilt.compiler)
    ksp(libs.raamcosta.destinations.ksp)
}

task("version") {
    doLast {
        println("versionName: " + android.defaultConfig.versionName)
    }
}
