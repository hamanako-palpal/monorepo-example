import type { CodegenConfig } from '@graphql-codegen/cli'
 
const config: CodegenConfig = {
  schema: '../../../schemas/graphql/*.graphqls',
  documents: '../../../schemas/graphql/*.gql',
  generates: {
    './src/graphql/generated.ts': {
      plugins: [
        'typescript',
        'typescript-operations',
        'graphql-codegen-svelte-apollo'
      ]
    }
  },
  config: {
    clientPath: "@generated/graphql/src/graphql/client"
  }
}
export default config