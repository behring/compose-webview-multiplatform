# Publishing Guide

This document explains how to publish and use the Compose WebView Multiplatform library.

## Publishing to GitHub Packages

### Prerequisites

1. **GitHub Personal Access Token** with the following permissions:
   - `repo` (Full control of private repositories)
   - `write:packages` (Upload packages to GitHub Package Registry)
   - `read:packages` (Download packages from GitHub Package Registry)

2. **Gradle Configuration** - The project is already configured for GitHub Packages publishing.

### Publishing Steps

1. **Configure GitHub Token** (if not already done):
   ```bash
   # Update ~/.gradle/gradle.properties
   GITHUB_USERNAME=behring
   GITHUB_TOKEN=your_github_personal_access_token
   ```

2. **Publish the Library**:
   ```bash
   ./gradlew :webview:publishAllPublicationsToGitHubPackagesRepository
   ```

3. **Verify Publication**:
   - Check [GitHub Packages](https://github.com/behring/compose-webview-multiplatform/packages)
   - The package should be visible as `io.github.behring:compose-webview-multiplatform`

## Using the Library

### Adding Repository

In your project's `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        // Add GitHub Packages repository
        maven {
            url = uri("https://maven.pkg.github.com/behring/compose-webview-multiplatform")
            credentials {
                username = "behring"  // GitHub username
                password = "your_github_token"  // GitHub Personal Access Token with read:packages permission
            }
        }
    }
}
```

### Adding Dependencies

#### For Kotlin Multiplatform Projects (Recommended)

In your `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.github.behring:compose-webview-multiplatform:1.0.0")
            }
        }
    }
}
```

#### For Android Projects

```kotlin
dependencies {
    implementation("io.github.behring:compose-webview-multiplatform-android:1.0.0")
}
```

#### For Desktop Projects

```kotlin
dependencies {
    implementation("io.github.behring:compose-webview-multiplatform-desktop:1.0.0")
}
```

### Usage Example

```kotlin
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.WebViewContent

@Composable
fun MyWebView() {
    val navigator = rememberWebViewNavigator()
    
    WebView(
        navigator = navigator,
        onCreated = { webView ->
            webView.loadUrl("https://example.com")
        }
    ) { state ->
        WebViewContent(state)
    }
}
```

## GitHub Token Requirements

### For Publishing (Full Access)
- **Permissions**: `repo`, `write:packages`, `read:packages`
- **Usage**: Publishing new versions of the library

### For Consumption (Read Only)
- **Permissions**: `read:packages`
- **Usage**: Downloading and using the library in other projects

## Version Management

- **Current Version**: `1.0.0`
- **Group ID**: `io.github.behring`
- **Artifact ID**: `compose-webview-multiplatform`

To publish a new version:
1. Update `VERSION_NAME` in `gradle.properties`
2. Run the publish command
3. Create a Git tag for the version

## Troubleshooting

### Common Issues

1. **401 Unauthorized**: Check your GitHub token permissions
2. **404 Not Found**: Ensure the repository URL is correct
3. **Dependency Resolution Issues**: Verify the repository is added to `settings.gradle.kts`

### Support

For issues related to this library, please create an issue on the [GitHub repository](https://github.com/behring/compose-webview-multiplatform).
