import Post from '#models/post'
import type { HttpContext } from '@adonisjs/core/http'
import fs from 'node:fs'
export default class PostsController {
  public async create({ view }: HttpContext) {
    return view.render('form')
  }

  public async store({ request, response }: HttpContext) {
    const { title, content } = request.only(['title', 'content'])

    await Post.create({ title, content })

    response.redirect('/posts')
  }

  public async index({ view }: HttpContext) {
    const posts = await Post.all()

    return view.render('posts', { posts })
  }

  public async saveToFile({ response }: HttpContext) {
    const posts = await Post.all()

    // Zapis postów do pliku JSON
    fs.writeFileSync('posts.json', JSON.stringify(posts, null, 2))

    response.redirect('/posts')
  }
}
