# Plan: Renombrar "habits" por "posts" en la app móvil

## Resumen de la información gathered:
La aplicación móvil tiene una estructura donde "habits" representa las publicaciones/posts del usuario. Necesitamos cambiar toda la nomenclatura de "habits" a "posts" incluyendo nombres de archivos, clases Java, variables, IDs de layouts y recursos.

## Archivos a renombrar (contenido y nombre):
1. `app/src/main/java/com/example/appmovil/Publicaciones/Habit.java` → `Post.java`
2. `app/src/main/java/com/example/appmovil/Fragments/KlyerHabitsFragment.java` → `KlyerPostsFragment.java`
3. `app/src/main/res/layout/fragment_habits.xml` → `fragment_posts.xml`
4. `app/src/main/res/drawable/bg_habit_type_badge.xml` → `bg_post_type_badge.xml`

## Archivos Java a modificar (solo contenido):
1. **AdapterFeed.java** - Cambiar `listaHabitos` → `listaPosts`, `Habit` → `Post`, `getHabitEmoji` → `getPostEmoji`
2. **KlyerFeed.java** - Cambiar `btnHabits` → `btnPosts`, `ivHabits` → `ivPosts`, `KlyerHabitsFragment` → `KlyerPostsFragment`, `R.id.btn_nav_habits` → `R.id.btn_nav_posts`, `R.id.iv_habits_icon` → `R.id.iv_posts_icon`
3. **Api_Gets.java** - Cambiar `HabitsCallback` → `PostsCallback`, `getHabits` → `getPosts`, `getHabitsByUserId` → `getPostsByUserId`, `Habit` → `Post`, variables `habits` → `posts`
4. **Api_Inserts.java** - Cambiar `addHabit` → `addPost`
5. **KlyerCameraFragment.java** - Cambiar `spinnerHabitType` → `spinnerPostType`, `habitTypes` → `postTypes`, `habitTypeEmojis` → `postTypeEmojis`, `postHabit()` → `postPublication()`, variables relacionadas
6. **KlyerFeedFragment.java** - Cambiar `listaHabitos` → `listaPosts`, `habits` → `posts`
7. **KlyerProfileFragment.java** - Cambiar `tvHabitsCount` → `tvPostsCount`

## Archivos XML a modificar:
1. **activity_feed.xml** - Cambiar IDs: `btn_nav_habits` → `btn_nav_posts`, `iv_habits_icon` → `iv_posts_icon`
2. **bottom_nav_menu.xml** - Cambiar ID: `nav_habits` → `nav_posts`
3. **strings.xml** - Cambiar string `nav_habits` → `nav_posts` (ya dice "Posts")
4. **item_post.xml** - Actualizar referencia `bg_habit_type_badge` → `bg_post_type_badge`

## Pasos a seguir:
1. Renombrar archivos físicos (Java y XML)
2. Actualizar contenido de cada archivo Java con los nuevos nombres
3. Actualizar IDs en archivos XML
4. Compilar y verificar que todo funcione

