# CustomSnackAlertView
Custom Snackbar

>Step 1. Add the JitPack repository to your build file

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  > Step 2. Add the dependency
  
  ```	dependencies {
	      implementation 'com.github.dalvik31:CustomSnackAlertView:Tag'
	}
  ```

>Step 3. Use

```
class MainActivity : AppCompatActivity() {

    //Initializate
    private val snackView: SnackAlertView by lazy { SnackAlertView(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        
        //Custom all
        snackView.load(
            data = Data(
                backgroundColor = android.R.color.holo_red_light,
                textColor = android.R.color.white,
                message = R.string.app_name,
                leftIconTint = android.R.color.white,
                rightIconTint = android.R.color.white,
                rightIcon = com.dalvik.customsnackbar.R.drawable.ic_cancel,
                leftIcon = com.dalvik.customsnackbar.R.drawable.ic_cancel,
                showOrientation = Gravity.RIGHT
            )
        )

         //Custom test
        //snackView.load(message = R.string.app_name)
        
        //Default
        //snackView.load()

    }
}
 ```
