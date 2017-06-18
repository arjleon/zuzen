# Zuzen
Lightweight form validation library for Android.

<a href="http://www.methodscount.com/?lib=com.github.arjleon%3Azuzen%3A-SNAPSHOT"><img src="https://img.shields.io/badge/Methods count-35-e91e63.svg"/></a>
<a href="http://www.methodscount.com/?lib=com.github.arjleon%3Azuzen%3A-SNAPSHOT"><img src="https://img.shields.io/badge/Size-6 KB-e91e63.svg"/></a>


The idea behind this library is to make form validation a bit easier with standardized classes that will deal with the actual validation to keep the view class (`Activity`, `Fragment`, `View`, etc.) as clean as possible on simple Android projects. It's set by linking specific classes of Views and their "Validators" that will know how to handle and validate the proper data before reporting back the result.

## Usage

### Dependency (via Jitpack.io)
Add jitpack's repo under `allrepositories` in your project's `build.gradle` file.
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Then add the library as a dependency under your module's build.gradle file. In the future once it gets on a higher, more stable version, it should have a release and a specific version could be defined. For now, stick to the exact same line as this snippet:
```
compile 'com.github.arjleon:zuzen:-SNAPSHOT'
```

### Implementation
Important list of the few classes in the library:
* `Validation`: Contains references of the View along with the `Validator` object that will perform the actual validation.
* `Validator`: Contains the actual algorithm processing the data from whatever `View` it's made to handle (e.g. `TextView`, `EditText`, or even a custom one if implemented.)
* `ValidationResult`: Returned when calling `Validation.validate()` and it has `boolean isValid()`, `T getFormView()`, and `Validator<T> getValidator()`.
* `ValidationSet`: Collection of `Validation` objects that can be created by chaining `Validation` objects via `ValidationSet Validation.chain(Validation)`. Also has a `validate()` method that will trigger the same method of each `Validation` object in order. Can continue to chain even more via `chain()`.

Check example below.

```java
// The library contains a BasicEmailValidator class implementing Validator<EditText>
final BasicEmailValidator emailAddressValidator = new BasicEmailValidator();
final Validation<EditText> emailVal = new Validation<>(mEmailEditText, emailAddressValidator);
// CustomView and CustomValidator are not real classes in the library
// They are part of the snippet to demonstrate the fact that custom implementations can be defined
final Validation<CustomView> customVal = new Validation<>(mCustomView, new CustomValidator());

...

// In custom TextWatcher implementations, we can run each individual validator to give real-time feedback if needed
ValidationResult realTimeCheck = emailVal.validate();

...

// And when a "Done" button is pressed, we can run all validations in order by chaining them all
//and show proper UI feedback until the ValidationResult is returning "true" for "isValid()"

final ValidationSet set = emailVal.chain(customVal); //more can be chained
...
ValidationResult finalCheck = set.validate(); //run validate on the final set with all chained validations

if (!finalCheck.isValid()) {
  showWrongInputInView(finalCheck.getFormView());
  return;
}

mPresenter.finishRegistration();
...
```

## What's with the name?
"Zuzen" means "correct" in the Basque language with the intention of making it unique while helping developers help their end users provide information in the correct and proper format.

## License
Apache License 2.0 already included in the repo itself.

## Testing
The library has unit tests covering all features but, as with everything, it can be improved, and hopefully that is something I'll be able to do moving forward as I constantly work on this albeit sporadically.

## Goals
While there are no other concrete goals other than work, fix, and tweak based on personal feedback, anything suggested by anyone interested is welcome. Check the next section.

## Feedback
Any feedback via issue tracker, and should it ever become really useful for fellow developers, we can improve on areas needed to make it more flexible while keeping it lightweight.
