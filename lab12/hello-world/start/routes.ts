/*
|--------------------------------------------------------------------------
| Routes file
|--------------------------------------------------------------------------
|
| The routes file is used for defining the HTTP routes.
|
*/

const PostsController = () => import('#controllers/posts_controller')
import router from '@adonisjs/core/services/router'

router.on('/').render('posts')
router.get('/posts/create', [PostsController, 'create']) // Formularz
router.post('/posts', [PostsController, 'store']) // Obsługa formularza
router.get('/posts', [PostsController, 'index']) // Wyświetlanie postów
router.get('/posts/save', [PostsController, 'saveToFile']) // Zapis do JSON
