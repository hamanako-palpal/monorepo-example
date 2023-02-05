import type { CodegenConfig } from '@graphql-codegen/cli'
 
const config: CodegenConfig = {
  schema: '../../../schemas/graphql/*.graphqls',
  generates: {
    './src/graphql/generated.ts': {
      plugins: [
        'typescript',
        'typescript-operations',
        'graphql-codegen-svelte-apollo'
      ]
    }
  }
}
export default config