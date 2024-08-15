# SE-LAB-AZ6

# فاز اول آزمایش

### پیاده‌سازی الگوی استراتژی با رویکرد توسعه آزمون‌محور (TDD)

در این پروژه، ما از الگوی طراحی استراتژی برای مدیریت مختلف روش‌های سفر و محاسبه زمان سفر بین شهرها استفاده کردیم. به این منظور، یک رابط به نام `TravelStrategy` تعریف شد که دو پیاده‌سازی مختلف دارد: `TrainTravelStrategy` برای محاسبه زمان سفر با قطار و `BusTravelStrategy` برای محاسبه زمان سفر با اتوبوس.

#### رویکرد آزمون‌محور (TDD)
رویکرد توسعه آزمون‌محور به ما کمک کرد تا قبل از نوشتن کد اصلی، آزمون‌هایی را برای تایید عملکرد مورد نظر بنویسیم. این آزمون‌ها شامل موارد زیر بودند:

**دو آزمون برای استراتژی قطار**: ابتدا آزمون‌هایی نوشته شد که بررسی می‌کرد زمان مورد انتظار سفر با قطار بین دو گره را محاسبه کند. این آزمون‌ها شکست خورده و نشان‌دهنده نیاز به پیاده‌سازی منطق محاسبه زمان سفر بودند.
   
**آزمون برای استراتژی اتوبوس**: مشابه با قطار، آزمون‌هایی برای بررسی عملکرد استراتژی اتوبوس نوشته شد. این آزمون‌ها به بررسی پیاده‌سازی صحیح محاسبه زمان سفر با توجه به وزن یال‌ها (که نمایانگر زمان است) پرداختند.

**پیاده‌سازی و تکرار**: پس از نوشتن هر آزمون، منطق مربوطه در کلاس‌های استراتژی پیاده‌سازی شد تا آزمون‌ها پاس شوند.

در عکس‌های زیر می‌توانید مراحل رویکرد توسعه آزمون‌محور را ببینید: <br> <br>
![5](https://github.com/user-attachments/assets/1b99887f-5801-4758-8c33-f82a8c20af98)
<br> <br>
سپس بعد از این مسئله قالب کلی کلاس را پیاده‌سازی کردیم: <br> <br>
![2](https://github.com/user-attachments/assets/e05b5d8c-b3ad-44f3-8591-4a039d6e1156)
![3](https://github.com/user-attachments/assets/e70bfbd3-2811-427d-98cc-248cf94f8e19)
<br> <br>
بعد از این تست‌ها را مجددا ران کردیم تا خطاهای زمان رانتایم مشخص شوند: <br> <br>
![1](https://github.com/user-attachments/assets/090adbd6-9412-45a1-aa72-aa5f281b4019)
<br> <br>
حال با توجه به این خطاها متدها را کامل کردیم که نتیجه‌ی اجرای مجدد آن‌ها را در زیر مشاهده می‌کنید: <br> <br>
![4](https://github.com/user-attachments/assets/24c7a540-7f20-4a93-bba2-32ae56723bc2)
<br> <br>
ما از الگوی طراحی استراتژی در پروژه استفاده کردیم تا بتوانیم روش‌های مختلف سفر و محاسبه زمان سفر بین شهرها را به صورت منعطف و قابل تعویض در زمان اجرا پیاده‌سازی کنیم. با تعریف رابط `TravelStrategy` و پیاده‌سازی‌های متفاوت آن برای قطار و اتوبوس، توانستیم رفتار محاسباتی مورد نظر را در قالب استراتژی‌های جداگانه ایزوله کرده و به کاربر اجازه دهیم تا بر اساس نیاز خود، روش محاسبه زمان سفر را انتخاب کند. این قابلیت تغییر رفتار محاسباتی بدون تغییر در کد اصلی نرم‌افزار و با استفاده از تزریق وابستگی، نشان‌دهنده موفقیت ما در پیاده‌سازی الگوی استراتژی است. این الگو نه تنها به ما امکان داد تا کد را با انعطاف بیشتری بنویسیم، بلکه به افزایش قابلیت نگهداری و تست‌پذیری کد نیز کمک کرد. <br> <br>

### پیاده‌سازی الگوی حالت یا وضعیت با رویکرد توسعه آزمون‌محور (TDD)

در پروژه ما، برای مدیریت حالت‌های مختلف مسیرهای میان شهرها از الگوی طراحی وضعیت (State) استفاده شد. این الگو به ما اجازه داد تا تغییرات حالت مسیرها، مانند تغییر بین "یک‌طرفه" و "دوطرفه"، را به صورت دینامیک و بدون نیاز به تغییر در ساختار اصلی کد انجام دهیم. برای پیاده‌سازی این الگو، از یک کلاس `enum` به نام `RouteState` استفاده کردیم که حالت‌های مختلف مسیر را شامل می‌شد. 

رویکرد توسعه آزمون‌محور (TDD) به ما کمک کرد تا ابتدا آزمون‌هایی برای بررسی تغییرات حالت بنویسیم. این آزمون‌ها به ما امکان داد تا رفتار مورد انتظار در هنگام تغییر وضعیت مسیرها را مشخص کنیم. به عنوان مثال، آزمون‌هایی نوشتیم که بررسی می‌کردند آیا مسیرها به درستی از حالت "دوطرفه" به "یک‌طرفه" تغییر می‌کنند و بالعکس.

پس از نوشتن این آزمون‌ها و اجرای آن‌ها، مشاهده شد که آزمون‌ها به دلیل نبود منطق مورد نیاز برای تغییر حالت‌ها شکست می‌خورند. در این مرحله، منطق مورد نیاز در کلاس `RouteManager` پیاده‌سازی شد. این کلاس شامل متدهایی مانند `setOneWay()` و `setTwoWay()` بود که با استفاده از آن‌ها می‌توانستیم حالت مسیرها را تغییر دهیم. همچنین، متدی به نام `updateRoutes()` برای اعمال تغییرات حالت‌ها بر روی تمامی مسیرها اضافه شد که بر اساس حالت جاری (`RouteState`) عمل می‌کرد.

هر بار پس از پیاده‌سازی منطق جدید، آزمون‌ها مجددا اجرا شدند تا اطمینان حاصل شود که تغییرات به درستی اعمال می‌شوند. با موفقیت‌آمیز بودن این آزمون‌ها، مطمئن شدیم که الگوی وضعیت به درستی پیاده‌سازی شده و تمامی حالات مسیرها به درستی مدیریت می‌شوند. استفاده از `enum` برای تعریف حالات مختلف نیز به ما کمک کرد تا کد تمیزتر و قابل نگهداری‌تری داشته باشیم و تغییرات در حالت‌ها را به راحتی مدیریت کنیم.

در عکس‌های زیر می‌توانید مراحل رویکرد توسعه آزمون‌محور را ببینید: <br> <br>
![image](https://github.com/user-attachments/assets/0bae1f79-be2d-4aa8-9d6f-78ac9b84ca16)
<br> <br>
سپس بعد از این مسئله قالب کلی کلاس را پیاده‌سازی کردیم: <br> <br>
![image](https://github.com/user-attachments/assets/14c5b551-6260-4bf9-b82c-5ae4be34a1c5)
![image](https://github.com/user-attachments/assets/cdfb4667-ced6-46d2-9788-1f3c52fe7436)
<br> <br>
بعد از این تست‌ها را مجددا ران کردیم تا خطاهای زمان رانتایم مشخص شوند: <br> <br>
![image](https://github.com/user-attachments/assets/566983b4-c4f1-44f3-983b-72eb4e7148fb)
![image](https://github.com/user-attachments/assets/0a588e03-0dcd-4884-bdf3-7242c41c2fb0)
<br> <br>
حال با توجه به این خطاها متدها را کامل کردیم که نتیجه‌ی اجرای مجدد آن‌ها را در زیر مشاهده می‌کنید: <br> <br>
![21](https://github.com/user-attachments/assets/8ece53c8-d873-4c4a-a075-1b10cd70a0b3)
<br> <br>
ما از الگوی طراحی وضعیت برای مدیریت تغییرات حالت مسیرها در پروژه استفاده کردیم و موفق شدیم این الگو را به طور کامل پیاده‌سازی کنیم. دلیل این موفقیت این است که توانستیم تغییر حالت‌ها را به سادگی و بدون نیاز به تغییرات عمده در کد اصلی، با استفاده از کلاس `RouteManager` و `enum` مرتبط با آن انجام دهیم. آزمون‌های متعددی که بر اساس رویکرد TDD نوشته و اجرا شدند، نشان دادند که تغییرات حالت به درستی اعمال شده و تمامی مسیرها مطابق انتظار به روزرسانی می‌شوند. این اطمینان از عملکرد صحیح، به ما نشان می‌دهد که الگوی وضعیت به طور موثر در این پروژه محقق شده است. <br> <br>


### تست کردن الگوریتم‌های گراف

در میان تست‌های بالا متوجه شدیم که یک تست به درستی جواب نمی‌دهد حال آنکه به نظر می‌رسید تمام منطق مورد نیاز تست‌ها به درستی پیاده‌سازی شده‌اند. پس از بررسی‌های بیشتر متوجه شدیم که ایراد از کلاس‌های جدید و متدهای نوشته شده‌ی آن‌ها نیست بلکه مشکل بخاطر نوع پیاده‌سازی الگوریتم‌های گراف در کلاس گراف بوده است. مشکل در بخش انتهایی که که مربوط به پیدا کردن مسیری که از شهر مورد نفرت عبور نکند بود. پس با توجه به رویکرد توسعه آزمون محور برای این دو الگوریتم در این رابطه تست‌هایی نوشتیم که در عکس زیر مشاهده می‌کنید: <br> <br>
![image](https://github.com/user-attachments/assets/d042dd97-f47e-4b35-be29-f835b8f4a3bb)
<br> <br>
همانطور که مشاهده می‌کنید تست‌ها فیل شدند. پس الگوریتم‌ها را اصلاح کردیم و تست‌ها را مجددا ران کردیم که نتیجه مانند تصویر زیر شد: <br> <br>
![image](https://github.com/user-attachments/assets/e9952998-d740-4dad-92b2-7cefffbd01db)
<br> <br>
### پیاده‌سازی کلاس Main با رویکرد توسعه آزمون‌محور (TDD)

کلاس `Main` در این پروژه به عنوان رابط کاربری اصلی عمل می‌کند و وظیفه مدیریت تعاملات کاربر با سیستم را بر عهده دارد. این کلاس ورودی‌های کاربر را دریافت می‌کند و با استفاده از متدهای مختلف، عملیات‌های مربوط به مدیریت مسیرها و محاسبه زمان سفر را انجام می‌دهد. این کلاس خود به طور مستقیم منطق پیچیده‌ای را پیاده‌سازی نمی‌کند، بلکه از سایر کلاس‌ها و استراتژی‌ها برای انجام محاسبات و تغییرات استفاده می‌کند.

کلاس `Main` شامل چندین متد کلیدی است که هر کدام وظیفه خاصی را بر عهده دارند. مهم‌ترین متد این کلاس، متد `main` است که به عنوان نقطه شروع برنامه عمل می‌کند. این متد ابتدا نقشه شهر و مسیرها را از کاربر دریافت می‌کند و سپس بر اساس نوع کاربر (استاندار یا شهروند)، منوی مربوطه را نمایش می‌دهد. بسته به انتخاب کاربر، متدهای مختلفی مانند `handleMayorActions` و `handleCitizenActions` فراخوانی می‌شوند.

متد `handleMayorActions` وظیفه مدیریت عملیات‌های مربوط به استاندار را دارد. این عملیات‌ها شامل تغییر وضعیت مسیرها بین یک‌طرفه و دوطرفه، و همچنین تنظیم سرعت قطارها است. این متد از کلاس `RouteManager` برای اعمال تغییرات در وضعیت مسیرها استفاده می‌کند.

متد `handleCitizenActions` نیز به شهروندان اجازه می‌دهد تا اطلاعات مربوط به زمان سفر را بر اساس استراتژی‌های مختلف (مانند استفاده از قطار یا اتوبوس) دریافت کنند. این متد از استراتژی‌های `TrainTravelStrategy` و `BusTravelStrategy` برای محاسبه زمان سفر استفاده می‌کند و نتایج را به کاربر نمایش می‌دهد.

همچنین، متد `createCityMapFromUserInput` برای دریافت نقشه شهر و مسیرها از کاربر و ساخت گراف مربوطه به کار می‌رود. این متد اطلاعات مربوط به شهرها و مسیرها را از کاربر می‌گیرد و گراف را با استفاده از کلاس‌های `Node` و `Edge` ایجاد می‌کند.

در نهایت، کلاس `Main` بیشتر به عنوان رابط کاربری عمل می‌کند و وظیفه اصلی آن هدایت کاربران به متدهای صحیح و نمایش نتایج به آن‌هاست، در حالی که منطق اصلی توسط سایر کلاس‌ها و استراتژی‌ها پیاده‌سازی می‌شود.

این کلاس‌ هم مانند موارد قبلی با رویکرد توسعه آزمون‌محور ایجاد شده است که نهایتا نتایج تست‌های آن را در تصویر زیر مشاهده می‌کنید: <br> <br>
![image](https://github.com/user-attachments/assets/71f502df-742c-427a-8b18-f5d3b4503a8d)
<br> <br>
نهایتا تصویری از پوشش تست‌ها آورده شده که حائز اهمیت است: <br> <br>
![image](https://github.com/user-attachments/assets/ea739d6e-ac94-4a9f-ae71-4a0160e00625)
<br> <br>
همانطور که مشاهده می‌کنید همه‌ی کلاس‌ها و متدها را تست‌ها پوشش داده‌اند. تنها در کلاس Main پوشش کامل نیست و آن هم به این علت است که تابع main تست نشده است که نیازی به این کار نیست زیرا صرفا تابع‌ها را صدا می‌زند. <br> <br>

## نتایج

برای این بخش یک فایل تکست به نام `result.txt` به بخش پوشه گراف پروژه اضافه شده است که خروجی یکبار ران کردن سیستم و کار کردن با آن و تست کردن حالات مختلف است. همچنین می‌توانید خودتان پروژه را ران کنید و با رابط کاربری آن ارتباط برقرار کنید و آن را تست کنید. (همانطور که در فایل تکست هم مشاهده می‌کنید سوالات و گزینه‌ها کاملا واضح هستند که مشکلی در برقراری ارتباط پیش نیاید.) <br> <br>








