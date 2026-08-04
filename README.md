# GutTrack — Android app

A native Kotlin + Jetpack Compose implementation of the GutTrack design (meal logging with
photos, symptom severity check-ins, timeline, PDF export, and reminder notifications).

This folder is a complete Gradle project (Home/Timeline/Export/Settings screens, Room database,
DataStore-backed settings, AlarmManager-based reminders, WorkManager follow-ups, and PDF
generation + share).

## 1. Install Android Studio (if you don't have it)

Download from https://developer.android.com/studio and install it. Any recent stable version
(2024.2.1 or newer) works with this project.

## 2. Open GutTrack in Android Studio

1. **File → Open** and pick this `GutTrack` folder.
2. Let it sync (**File → Sync Project with Gradle Files** if it doesn't do so automatically).

## 3. Run it on your Pixel 10 Pro

1. On the phone: **Settings → About phone** → tap **Build number** 7 times to enable Developer
   options, then **Settings → System → Developer options → USB debugging** (or **Wireless
   debugging** if you'd rather skip the cable).
2. Connect the phone (USB, or pair via Wireless debugging), accept the "Allow USB debugging"
   prompt on the phone.
3. In Android Studio, select your Pixel from the device dropdown and click **Run ▶**.
4. First launch will ask for notification permission (for meal/symptom reminders) — allow it.
   The app also shows a "Enable exact-time reminders" button on the Settings tab if Android
   hasn't yet granted exact-alarm scheduling; tap it once to flip that on in system settings.

1. On the phone: **Settings → About phone** → tap **Build number** 7 times to enable Developer
   options, then **Settings → System → Developer options → USB debugging** (or **Wireless
   debugging** if you'd rather skip the cable).
2. Connect the phone (USB, or pair via Wireless debugging), accept the "Allow USB debugging"
   prompt on the phone.
3. In Android Studio, select your Pixel from the device dropdown and click **Run ▶**.
4. First launch will ask for notification permission (for meal/symptom reminders) — allow it.
   The app also shows a "Enable exact-time reminders" button on the Settings tab if Android
   hasn't yet granted exact-alarm scheduling; tap it once to flip that on in system settings.

From then on, the app lives on your phone like any other — no cable needed to keep using it. To
push updates later, just click Run ▶ again while connected (or use **Build → Generate Signed
Bundle/APK** to produce an installable `.apk`/`.aab` you can transfer directly).

## What's implemented

- **Home** — today's next-meal card, meal chips (breakfast/lunch/dinner/snack), symptom
  check-in shortcut, and today's log.
- **Timeline** — last 7 days, day-by-day.
- **Export** — report preview grouped by day, "include photos" toggle, and a real PDF generated
  on-device (`android.graphics.pdf.PdfDocument`) shared via Android's share sheet (email, Drive,
  Messages, etc.) — enter your nutrition expert's email on this screen to prefill the recipient.
- **Settings** — tracking-period progress bar, per-meal reminder times, follow-up/snack-ask
  toggles, and a test-notification button.
- **Photos** — tap a meal's photo slot to take a picture with the camera or pick one from your
  gallery (Android's Photo Picker — no storage permission needed).
- **Reminders** — `AlarmManager`-scheduled daily notifications for breakfast/lunch/dinner (and
  an optional afternoon snack check-in), a 45-minute follow-up nudge via `WorkManager` if a meal
  wasn't logged, and rescheduling after device reboot.
- **Data** — meals and symptoms persist locally in a Room database; settings persist in
  DataStore. Nothing leaves the device except the PDF you explicitly choose to share.

## Notes / things you may want to adjust

- The launcher icon is a simple vector (no custom artwork) — swap
  `res/drawable/ic_launcher_foreground.xml` for something nicer if you like.
- `applicationId` is `com.guttrack.app` — change it in `app/build.gradle.kts` if you want a
  different package.
- The "nutrition expert" email field is stored locally and only used to prefill the share sheet;
  there's no backend, so nothing is sent automatically in the background.
