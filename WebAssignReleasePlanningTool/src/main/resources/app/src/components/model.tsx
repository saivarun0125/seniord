export interface ReleaseAction {
    id: String,
    name: String,
    notes: String,
    duration: String,
    repository?: {
        id: String,
        name: String
    },
    action?: String
}