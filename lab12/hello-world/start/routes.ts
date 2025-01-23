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
router.get('/posts/create', [PostsController, 'create'])
router.post('/posts', [PostsController, 'store'])
router.get('/posts', [PostsController, 'index'])
router.get('/posts/save', [PostsController, 'saveToFile'])
