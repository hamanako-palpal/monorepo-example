import { SignUp } from "@generated/graphql/src/graphql/generated"
import type { Actions, PageLoad } from './$types';

export const actions: Actions = {
  default: async ({ request, cookies }) => {
      const form = await request.formData();
      const email = form.get('email');
      const userName = form.get('userName');
      const password = form.get('password');

      if (typeof email !== 'string' || typeof password !== 'string' || typeof userName !== 'string')
        return null;

      const res = await SignUp({
        variables: {
          userName: userName,
          email: email,
          password: password,
        }
      });
      cookies.set('session', res.data?.signUp?.id.toString()!!, {
          path: '/',
          httpOnly: true,
          sameSite: 'strict',
          secure: false,
          maxAge: 60 * 60 * 24 * 30
      });
  }
};