# Material Icons for Vaadin add-on

[![Build Status](https://epic.siika.fi/jenkins/job/MaterialIcons%20(Vaadin)/badge/icon)](https://epic.siika.fi/jenkins/job/MaterialIcons%20(Vaadin)/)

Material Icons for Vaadin add-on brings Google's Material Icons to your Vaadin applications.

Online demo: http://app.siika.fi/MaterialIconsDemo

## Release Log

### 3.0.1 (2017-06-19)
* Move font-face definition outside add-on mixin. This should remove requirement to manually add it to
application theme.
* Update version number to follow the version number of Material Icons by Google
* Mark as STABLE

### 0.3.0.1 (2017-06-15)
* Initial release
* Material Design Icons by Google version 3.0.1

## Usage

### Project setup
Make sure your project has theme updating enabled, and addons.scss is included to your theme.

This will make sure font gets defined correctly. All other CSS rules will come automatically from
included addons SCSS rules.

### In Java code
Just use MaterialIcons enumeration class to receive FontIcon you want.
```java
Button button = new Button(MaterialIcons.SENTIMENT_SATISFIED);
```

### How to find correct icon to use
Demo application of this add-on can be used to find suitable icon, and get matching enumeration for
Java code, and CSS sniplet for your theming usage. http://app.siika.fi/MaterialIconsDemo

Or you can browse icons at google's page: https://material.io/icons/

## Font Missing
First you need to have own custom SCSS based theme on your application to get add-on's CSS rules
compiled in. Also your theme should import and include addons. Also add-ons should upgraded
automatically or by hand to import and include materialicons.

Font-face definition should get included to application as long as addon's scss file gets compiled
to the theme correctly. How ever if this does not work, try adding this to start of your theme's scss.

```css
@font-face {
  font-family: 'MaterialIcons';
  font-style: normal;
  font-weight: 400;
  src: local('Material Icons'),
  local('MaterialIcons-Regular'),
  url(../../../VAADIN/addons/materialicons/MaterialIcons-Regular.woff2) format('woff2'),
  url(../../../VAADIN/addons/materialicons/MaterialIcons-Regular.woff) format('woff'),
  url(../../../VAADIN/addons/materialicons/MaterialIcons-Regular.ttf) format('truetype');
}
```


##  Maintenance

### How to update add-on to have new icons
When new icons have been added to material icons, here is how to get this project updated to include those.

* Download new webicon files from: https://github.com/google/material-design-icons/tree/master/iconfont
* Replace old webicon files at:
```materialicons-addon/src/resources/VAADIN/addons/materialicons```
* Download new codepoints file from: https://github.com/google/material-design-icons/blob/master/iconfont/codepoints
* Replace ```materialicons-addon/src/test/resources/codepoints``` file with new codepoints file
* Run materialicons-addon test cases. Test(s) should fail and generate code sniplet that can be used to replace the old outdated
in ```MaterialIcons``` class.
* Rerun tests, those should pass now
* Increase add-on version, package/install it and start using new version

Now you have version of addon with updated set of icons.

## License

Both this add-on and Material Design Icons are under Apache 2.0 license
https://www.apache.org/licenses/LICENSE-2.0

Material Design Icons and codepoints file are from Google:
https://github.com/google/material-design-icons/