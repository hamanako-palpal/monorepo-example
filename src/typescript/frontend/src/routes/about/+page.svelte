<script lang="ts">
  import { page } from "$app/stores"
</script>

<h1>Example Auth</h1>
<p>
  {#if $page.data.session}
    {#if $page.data.session.user}
      hi {$page.data.session.user.name} :)
    {/if}
    zzz..
  {:else}
  <form>
    <div>
      <label for="username">Username</label>
      <input id="username" name="username" type="text" required />
    </div>

    <div>
      <label for="email">email</label>
      <input id="email" name="email" type="text" required />
    </div>
  
    <div>
      <label for="password">Password</label>
      <input id="password" name="password" type="password" required />
    </div>
  
      <button type="submit">Register</button>
  </form>
  {/if}
</p>
