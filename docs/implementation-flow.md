# StoraX AI Roadmap Checklist



Product goal: build an intelligent cloud storage platform that combines secure file management, AI-powered document understanding, semantic search, and backend automation.



## Phase 0: Project Foundation



### Application Setup

- [ ] Define package structure for auth, user, storage, sharing, ai, search, automation, audit, and common

- [ ] Add environment-based configuration for dev, test, and prod

- [ ] Add PostgreSQL schema and connection settings

- [ ] Add object storage strategy for file blobs

- [ ] Add API documentation setup with OpenAPI

- [ ] Add centralized response format for success and error payloads

- [ ] Add UUID strategy for entities

- [ ] Add base timestamps and audit columns



### Cross-Cutting Services

- [ ] Add validation handling service

- [ ] Add exception mapping service

- [ ] Add tracking ID generation

- [ ] Add password encoding service

- [ ] Add security configuration service

- [ ] Add request logging and response logging



## Phase 1: Authentication and User Services



### `POST /api/auth/register`

- [ ] Validate username, email, and password

- [ ] Check for duplicate email before save

- [ ] Hash password before persistence

- [ ] Create user with default role

- [ ] Return registration response with user id, email, and username

- [ ] Add duplicate user error handling

- [ ] Add integration test for success case

- [ ] Add integration test for duplicate email case



### `POST /api/auth/login`

- [ ] Validate login request payload

- [ ] Verify user exists by email

- [ ] Match password with stored hash

- [ ] Generate access token

- [ ] Generate refresh token

- [ ] Return token pair with expiry details

- [ ] Add invalid credential error handling

- [ ] Add login success test

- [ ] Add invalid password test



### `POST /api/auth/refresh`

- [ ] Accept refresh token

- [ ] Validate token signature and expiry

- [ ] Rotate access token

- [ ] Rotate refresh token if needed

- [ ] Revoke reused or expired refresh tokens

- [ ] Add refresh token test coverage



### `GET /api/users/me`

- [ ] Extract authenticated user from security context

- [ ] Return current user profile

- [ ] Hide password hash from response

- [ ] Add profile endpoint test



### `UserService`

- [ ] Add create-user method

- [ ] Add find-user-by-email method

- [ ] Add find-user-by-id method

- [ ] Add update-profile method

- [ ] Add deactivate-account method

- [ ] Add user role lookup method



### `AuthService`

- [ ] Keep register use case isolated in service layer

- [ ] Add login use case

- [ ] Add refresh-token use case

- [ ] Add token revocation use case

- [ ] Add password verification helper

- [ ] Add duplicate email guard



### `UserRepository`

- [ ] Support email lookup

- [ ] Support id lookup

- [ ] Support email existence check

- [ ] Add optional username search



## Phase 2: Core Storage Services



### `POST /api/files/upload`

- [ ] Validate file size and type

- [ ] Validate authenticated user access

- [ ] Compute file hash for duplicate detection

- [ ] Store file in object storage

- [ ] Persist file metadata in database

- [ ] Mark file status as uploaded

- [ ] Return file id, name, size, and status

- [ ] Add upload success test

- [ ] Add invalid file test



### `GET /api/files/{id}/download`

- [ ] Check file ownership or shared access

- [ ] Fetch file metadata

- [ ] Stream file from storage

- [ ] Return correct content type and content disposition

- [ ] Add download test for owner

- [ ] Add forbidden access test



### `DELETE /api/files/{id}`

- [ ] Verify delete permission

- [ ] Soft delete file metadata

- [ ] Remove or mark blob for cleanup

- [ ] Write audit log entry

- [ ] Add delete test



### `PATCH /api/files/{id}`

- [ ] Support rename file

- [ ] Support move file to another folder

- [ ] Support updating file description or labels

- [ ] Validate target folder ownership

- [ ] Add patch request tests



### `POST /api/folders`

- [ ] Validate folder name

- [ ] Create folder under optional parent

- [ ] Enforce unique sibling folder names

- [ ] Return folder id and path

- [ ] Add folder creation test



### `GET /api/folders/{id}`

- [ ] Return folder details

- [ ] Return child folders

- [ ] Return contained files

- [ ] Enforce access control



### `POST /api/shares`

- [ ] Create share link

- [ ] Support expiry time

- [ ] Support permission level

- [ ] Support public or restricted share mode

- [ ] Add share creation test



### `DELETE /api/shares/{id}`

- [ ] Revoke share link

- [ ] Remove access for invited users

- [ ] Write audit log entry



### `FileService`

- [ ] Handle file upload workflow

- [ ] Handle file download workflow

- [ ] Handle file delete workflow

- [ ] Handle file rename workflow

- [ ] Handle file move workflow

- [ ] Handle duplicate detection workflow



### `FolderService`

- [ ] Create folder

- [ ] Resolve folder tree

- [ ] Move folder

- [ ] Rename folder

- [ ] Prevent circular folder references



### `SharingService`

- [ ] Create share link

- [ ] Validate share access

- [ ] Revoke share link

- [ ] Track share expiry



### `StorageRepository`

- [ ] Persist file metadata

- [ ] Persist folder metadata

- [ ] Persist share metadata

- [ ] Persist file version metadata

- [ ] Support folder tree queries



## Phase 3: Versioning and Audit



### `POST /api/files/{id}/versions`

- [ ] Save previous file version

- [ ] Create new version record on upload

- [ ] Track version number

- [ ] Add version creation test



### `GET /api/files/{id}/versions`

- [ ] Return list of file versions

- [ ] Return metadata for each version

- [ ] Enforce access rules



### `POST /api/files/{id}/versions/{versionId}/restore`

- [ ] Validate restore permission

- [ ] Replace active file reference

- [ ] Write audit log entry

- [ ] Add restore test



### `AuditService`

- [ ] Track upload events

- [ ] Track delete events

- [ ] Track share events

- [ ] Track login events

- [ ] Track AI action events

- [ ] Store actor, action, target, and timestamp



### `AuditLogRepository`

- [ ] Persist audit entries

- [ ] Support filtering by user

- [ ] Support filtering by action type

- [ ] Support filtering by resource id



## Phase 4: AI Chat and Tool Calling



### `POST /api/ai/chat`

- [ ] Accept user message

- [ ] Load user context and permissions

- [ ] Decide whether to answer directly or call tools

- [ ] Support streaming response mode

- [ ] Persist chat history

- [ ] Add chat endpoint tests



### `AIService`

- [ ] Build prompt composition layer

- [ ] Add system instructions

- [ ] Add conversation memory

- [ ] Add tool selection logic

- [ ] Add response formatting logic

- [ ] Add safety checks for file actions



### `ToolCallingService`

- [ ] Define tool input schema

- [ ] Define tool output schema

- [ ] Add tool routing to backend services

- [ ] Add permission checks before tool execution

- [ ] Add tool result normalization



### AI Tools

- [ ] Implement upload tool

- [ ] Implement delete tool

- [ ] Implement move tool

- [ ] Implement rename tool

- [ ] Implement share tool

- [ ] Implement search tool

- [ ] Implement summarize tool

- [ ] Implement OCR tool

- [ ] Implement tag tool

- [ ] Implement archive tool



### `ChatHistoryService`

- [ ] Store conversation messages

- [ ] Load prior context for a user

- [ ] Support conversation listing

- [ ] Support conversation deletion



## Phase 5: OCR, Summarization, and Auto-Tagging



### `POST /api/ai/ocr`

- [ ] Accept image or scanned PDF

- [ ] Extract raw text

- [ ] Store OCR result

- [ ] Return text with confidence if available

- [ ] Add OCR success test



### `POST /api/ai/summarize`

- [ ] Accept file id or text input

- [ ] Generate short summary

- [ ] Generate detailed summary

- [ ] Persist summary output

- [ ] Add summary endpoint test



### `POST /api/ai/tag`

- [ ] Analyze file content

- [ ] Suggest tags

- [ ] Suggest category

- [ ] Save approved tags

- [ ] Add tagging test



### `DocumentProcessingService`

- [ ] Extract text from PDF

- [ ] Extract text from DOCX

- [ ] Extract text from images

- [ ] Normalize extracted content

- [ ] Detect language if needed

- [ ] Send text to downstream AI services



### `TaggingService`

- [ ] Generate candidate tags

- [ ] Rank tags by confidence

- [ ] Map content to category

- [ ] Store manual override tags



## Phase 6: RAG and Semantic Search



### `POST /api/search/semantic`

- [ ] Accept natural-language query

- [ ] Convert query to embedding

- [ ] Search across file chunks

- [ ] Rank by similarity

- [ ] Filter by user permissions

- [ ] Return matched files and chunks

- [ ] Add semantic search test



### `POST /api/ai/ask`

- [ ] Accept document question

- [ ] Retrieve relevant chunks

- [ ] Compose grounded answer

- [ ] Return citations

- [ ] Add question-answer test



### `EmbeddingService`

- [ ] Generate embeddings for file chunks

- [ ] Generate embeddings for user queries

- [ ] Persist embedding metadata

- [ ] Support batch embedding jobs



### `ChunkingService`

- [ ] Split text by token or paragraph strategy

- [ ] Preserve page references

- [ ] Preserve section boundaries

- [ ] Store chunk order



### `VectorSearchService`

- [ ] Store vector records

- [ ] Retrieve nearest neighbors

- [ ] Apply metadata filters

- [ ] Apply permission filters

- [ ] Return source references



## Phase 7: Automation Workflows



### `POST /api/automation/rules`

- [ ] Create automation rule

- [ ] Validate trigger type

- [ ] Validate action type

- [ ] Attach rule to workspace

- [ ] Add rule creation test



### `POST /api/webhooks/file-uploaded`

- [ ] Trigger workflow on upload

- [ ] Extract invoice vendor

- [ ] Extract invoice amount

- [ ] Categorize invoice

- [ ] Generate summary

- [ ] Notify user



### `AutomationService`

- [ ] Evaluate upload triggers

- [ ] Evaluate document-type triggers

- [ ] Evaluate time-based triggers

- [ ] Execute chained actions

- [ ] Retry failed workflows

- [ ] Record workflow status



### `NotificationService`

- [ ] Send in-app notification

- [ ] Send email notification

- [ ] Send workflow completion notification

- [ ] Send failure notification



## Phase 8: Distributed Backend and Platform Services



### `CacheService`

- [ ] Add Redis cache for frequent lookups

- [ ] Cache user permissions

- [ ] Cache folder tree snapshots

- [ ] Cache hot search results



### `QueueService`

- [ ] Publish file processing jobs

- [ ] Publish OCR jobs

- [ ] Publish embedding jobs

- [ ] Publish automation jobs

- [ ] Add retry and dead-letter handling



### `SchedulerService`

- [ ] Clean temporary files

- [ ] Archive old files

- [ ] Reprocess failed jobs

- [ ] Recalculate stale embeddings

- [ ] Send periodic summary notifications



### `ObservabilityService`

- [ ] Add structured logs

- [ ] Add request tracing

- [ ] Add metrics for upload latency

- [ ] Add metrics for AI latency

- [ ] Add metrics for queue latency

- [ ] Add health checks



## Phase 9: Product Polish



### Workspace and Team Features

- [ ] Add workspace creation

- [ ] Add team invites

- [ ] Add team roles

- [ ] Add organization-level quotas

- [ ] Add admin controls



### UX and Reporting

- [ ] Add favorites

- [ ] Add activity timeline

- [ ] Add file comments

- [ ] Add search history

- [ ] Add analytics dashboard

- [ ] Add storage usage dashboard



### Production Readiness

- [ ] Add Docker support

- [ ] Add CI pipeline

- [ ] Add CD pipeline

- [ ] Add test coverage reports

- [ ] Add API rate limiting

- [ ] Add security headers



## Suggested Build Order



1. Finish authentication and authorization

2. Build file upload, download, and metadata storage

3. Add folder hierarchy and sharing

4. Add version history and audit logs

5. Add async processing with queue support

6. Add OCR and summarization

7. Add embeddings and semantic search

8. Add AI chat with tool calling

9. Add automation workflows

10. Add Redis, logs, metrics, and observability



## Resume Title



Recommended project title:



- StoraX AI - Intelligent Cloud Storage Platform



## Short Product Description



StoraX AI is an intelligent cloud storage platform that combines secure file management, AI-powered document understanding, semantic search, and automated workflows.