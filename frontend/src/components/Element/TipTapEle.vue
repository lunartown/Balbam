<template>
  <div class="editor">
    <editor-menu-bar
      v-if="swMenubar"
      :editor="editor"
      v-slot="{ commands, isActive, getMarkAttrs }"
      style="position: fix"
    >
      <div class="menubar">
        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.bold() }"
          @click.prevent="commands.bold"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path
              d="M15.6 10.79c.97-.67 1.65-1.77 1.65-2.79 0-2.26-1.75-4-4-4H7v14h7.04c2.09 0 3.71-1.7 3.71-3.79 0-1.52-.86-2.82-2.15-3.42zM10 6.5h3c.83 0 1.5.67 1.5 1.5s-.67 1.5-1.5 1.5h-3v-3zm3.5 9H10v-3h3.5c.83 0 1.5.67 1.5 1.5s-.67 1.5-1.5 1.5z"
            />
          </svg>
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.italic() }"
          @click.prevent="commands.italic"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path d="M10 4v3h2.21l-3.42 8H6v3h8v-3h-2.21l3.42-8H18V4z" />
          </svg>
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.strike() }"
          @click.prevent="commands.strike"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            enable-background="new 0 0 24 24"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <g><rect fill="none" height="24" width="24" /></g>
            <g>
              <g>
                <g>
                  <path
                    d="M6.85,7.08C6.85,4.37,9.45,3,12.24,3c1.64,0,3,0.49,3.9,1.28c0.77,0.65,1.46,1.73,1.46,3.24h-3.01 c0-0.31-0.05-0.59-0.15-0.85c-0.29-0.86-1.2-1.28-2.25-1.28c-1.86,0-2.34,1.02-2.34,1.7c0,0.48,0.25,0.88,0.74,1.21 C10.97,8.55,11.36,8.78,12,9H7.39C7.18,8.66,6.85,8.11,6.85,7.08z M21,12v-2H3v2h9.62c1.15,0.45,1.96,0.75,1.96,1.97 c0,1-0.81,1.67-2.28,1.67c-1.54,0-2.93-0.54-2.93-2.51H6.4c0,0.55,0.08,1.13,0.24,1.58c0.81,2.29,3.29,3.3,5.67,3.3 c2.27,0,5.3-0.89,5.3-4.05c0-0.3-0.01-1.16-0.48-1.94H21V12z"
                  />
                </g>
              </g>
            </g>
          </svg>
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.underline() }"
          @click.prevent="commands.underline"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path
              d="M12 17c3.31 0 6-2.69 6-6V3h-2.5v8c0 1.93-1.57 3.5-3.5 3.5S8.5 12.93 8.5 11V3H6v8c0 3.31 2.69 6 6 6zm-7 2v2h14v-2H5z"
            />
          </svg>
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.code() }"
          @click.prevent="commands.code"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0V0z" fill="none" />
            <path
              d="M9.4 16.6L4.8 12l4.6-4.6L8 6l-6 6 6 6 1.4-1.4zm5.2 0l4.6-4.6-4.6-4.6L16 6l6 6-6 6-1.4-1.4z"
            />
          </svg>
        </button>

        <button
          class="menubar__button heading"
          :class="{ 'is-active': isActive.heading({ level: 1 }) }"
          @click.prevent="commands.heading({ level: 1 })"
        >
          H1
        </button>

        <button
          class="menubar__button heading"
          :class="{ 'is-active': isActive.heading({ level: 2 }) }"
          @click.prevent="commands.heading({ level: 2 })"
        >
          H2
        </button>

        <button
          class="menubar__button heading"
          :class="{ 'is-active': isActive.heading({ level: 3 }) }"
          @click.prevent="commands.heading({ level: 3 })"
        >
          H3
        </button>
        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.paragraph() }"
          @click.prevent="commands.paragraph"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path
              d="M3 15h18v-2H3v2zm0 4h18v-2H3v2zm0-8h18V9H3v2zm0-6v2h18V5H3z"
            />
          </svg>
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.bullet_list() }"
          @click.prevent="commands.bullet_list"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0V0z" fill="none" />
            <path
              d="M4 10.5c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5zm0-6c-.83 0-1.5.67-1.5 1.5S3.17 7.5 4 7.5 5.5 6.83 5.5 6 4.83 4.5 4 4.5zm0 12c-.83 0-1.5.68-1.5 1.5s.68 1.5 1.5 1.5 1.5-.68 1.5-1.5-.67-1.5-1.5-1.5zM7 19h14v-2H7v2zm0-6h14v-2H7v2zm0-8v2h14V5H7z"
            />
          </svg>
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.ordered_list() }"
          @click.prevent="commands.ordered_list"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path
              d="M2 17h2v.5H3v1h1v.5H2v1h3v-4H2v1zm1-9h1V4H2v1h1v3zm-1 3h1.8L2 13.1v.9h3v-1H3.2L5 10.9V10H2v1zm5-6v2h14V5H7zm0 14h14v-2H7v2zm0-6h14v-2H7v2z"
            />
          </svg>
        </button>

        <button
          class="menubar__button"
          :class="{ 'is-active': isActive.blockquote() }"
          @click.prevent="commands.blockquote"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path d="M6 17h3l2-4V7H5v6h3zm8 0h3l2-4V7h-6v6h3z" />
          </svg>
        </button>
        <button
          class="menubar__button"
          @click.prevent="commands.horizontal_rule"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            enable-background="new 0 0 24 24"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <g>
              <rect fill="none" fill-rule="evenodd" height="24" width="24" />
              <rect fill-rule="evenodd" height="2" width="16" x="4" y="11" />
            </g>
          </svg>
        </button>
        <button class="menubar__button" @click.prevent="commands.undo">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path
              d="M12.5 8c-2.65 0-5.05.99-6.9 2.6L2 7v9h9l-3.62-3.62c1.39-1.16 3.16-1.88 5.12-1.88 3.54 0 6.55 2.31 7.6 5.5l2.37-.78C21.08 11.03 17.15 8 12.5 8z"
            />
          </svg>
        </button>

        <button class="menubar__button" @click.prevent="commands.redo">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path
              d="M18.4 10.6C16.55 8.99 14.15 8 11.5 8c-4.65 0-8.58 3.03-9.96 7.22L3.9 16c1.05-3.19 4.05-5.5 7.6-5.5 1.95 0 3.73.72 5.12 1.88L13 16h9V7l-3.6 3.6z"
            />
          </svg>
        </button>
        <button
          class="menubar__button"
          :class="{
            'is-disabled': shouldDisableButton(isActive.link()),
            'is-active': isActive.link(),
          }"
          @click.prevent="
            isActive.link()
              ? changeLinkDialog(commands.link)
              : addLinkDialog(commands.link, getMarkAttrs('link'))
          "
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path
              d="M3.9 12c0-1.71 1.39-3.1 3.1-3.1h4V7H7c-2.76 0-5 2.24-5 5s2.24 5 5 5h4v-1.9H7c-1.71 0-3.1-1.39-3.1-3.1zM8 13h8v-2H8v2zm9-6h-4v1.9h4c1.71 0 3.1 1.39 3.1 3.1s-1.39 3.1-3.1 3.1h-4V17h4c2.76 0 5-2.24 5-5s-2.24-5-5-5z"
            />
          </svg>
        </button>
        <button
          class="menubar__button"
          @click.prevent="insertImage(commands.image)"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <path d="M0 0h24v24H0z" fill="none" />
            <path
              d="M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16H5V5h14v14zm-5.04-6.71l-2.75 3.54-1.96-2.36L6.5 17h11l-3.54-4.71z"
            />
          </svg>
        </button>
        <button
          v-if="isArticle"
          class="menubar__button"
          @click.prevent="uploadImage(commands.image)"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            enable-background="new 0 0 24 24"
            height="24"
            viewBox="0 0 24 24"
            width="24"
          >
            <g><rect fill="none" height="24" width="24" /></g>
            <g><path d="M5,20h14v-2H5V20z M5,10h4v6h6v-6h4l-7-7L5,10z" /></g>
          </svg>
        </button>
      </div>
    </editor-menu-bar>
    <editor-content class="editor__content" :editor="editor" />
  </div>
</template>

<script>
import { Editor, EditorContent, EditorMenuBar } from "tiptap";
import {
  Blockquote,
  HardBreak,
  Heading,
  HorizontalRule,
  OrderedList,
  BulletList,
  ListItem,
  TodoItem,
  TodoList,
  Bold,
  Code,
  Italic,
  Strike,
  Underline,
  History,
  Image,
  Link,
} from "tiptap-extensions";
const Swal = require("sweetalert2");
export default {
  components: {
    EditorContent,
    EditorMenuBar,
  },
  props: ["description", "menubar", "readOnly", "isArticle"],
  data() {
    return {
      swMenubar: this.menubar,
      editor: null,
      imgsExtenstion: ["jpg", "png", "svg", "jpeg", "gif", "bmp"],
    };
  },
  mounted() {
    this.editor = new Editor({
      editable: !this.readOnly,
      extensions: [
        new Blockquote(),
        new BulletList(),
        new HardBreak(),
        new Heading({ levels: [1, 2, 3] }),
        new ListItem(),
        new Link({
          openOnClick: true,
          target: "_blank",
        }),
        new OrderedList(),
        new TodoItem(),
        new TodoList(),
        new Bold(),
        new Code(),
        new Italic(),
        new Strike(),
        new Underline(),
        new History(),
        new HorizontalRule(),
        new Image(),
      ],
      content: this.description,
      onUpdate: () => {
        this.$emit("editorContent", { val: this.editor.getHTML() });
      },
    });
  },

  beforeDestroy() {
    this.editor.destroy();
  },

  methods: {
    shouldDisableButton: function (isActive) {
      return !isActive & window.getSelection().isCollapsed;
    },
    addLinkDialog: async function (command) {
      if (window.getSelection().isCollapsed) {
        return;
      }
      let res;
      await Swal.fire({
        title: "Url을 입력해주세요.",
        input: "url",
        inputPlaceholder: "Enter the http://...",
        showCancelButton: true,
        showCloseButton: true,
        cancelButtonText: "취소",
        confirmButtonText: "등록",
        preConfirm: (url) => {
          res = url;
        },
      });

      if (res && res != "https://" && res != "http://") {
        command({ href: res });
      }
    },
    changeLinkDialog: async function (command) {
      let res;

      await Swal.fire({
        title: "변경할 Url을 입력해주세요.",
        input: "url",
        inputPlaceholder: "Enter the http://...",
        showCancelButton: true,
        showCloseButton: true,
        cancelButtonText: "취소",
        confirmButtonText: "등록",
        preConfirm: (url) => {
          res = url;
        },
      });

      if (res != undefined) {
        if (res == "http://" || res == "https://") {
          res = "";
        }
        command({ href: res });
      }
    },
    async insertImage(command) {
      let src;
      await Swal.fire({
        title: "이미지 Url을 입력해주세요.",
        input: "url",
        inputPlaceholder: "Enter the http://...",
        showCancelButton: true,
        showCloseButton: true,
        cancelButtonText: "취소",
        confirmButtonText: "등록",
        preConfirm: (url) => {
          src = url;
        },
      });
      if (src) command({ src });
    },
    async uploadImage(command) {
      let file;
      await Swal.fire({
        title: "이미지를 올려주세요(이미지만 가능합니다.)",
        input: "file",
        showCancelButton: true,
        showCloseButton: true,
        cancelButtonText: "취소",
        confirmButtonText: "등록",
        preConfirm: (image) => {
          file = image;
        },
      });
      if (file == null) return;
      let flag = false;
      for (var i = 0; i < this.imgsExtenstion.length; i++) {
        let len = file.name.lastIndexOf(this.imgsExtenstion[i]);
        if (len >= file.name.length - 5) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: "Something went wrong!",
        });
        return;
      }
      if (file && flag) {
        const reader = new FileReader();
        reader.onload = (e) => {
          Swal.fire({
            title: "Your uploaded picture",
            imageUrl: e.target.result,
            imageAlt: "The uploaded picture",
          });
          command({ src: e.target.result });
        };
        reader.readAsDataURL(file);
      }
    },
  },
};
</script>

<style lang="scss">
.heading {
  font-size: 1rem;
  vertical-align: super;
}
.editor {
  position: relative;
  font-weight: 100;
  .menubar {
    text-align: center;
    border-bottom: 1px solid #ddd;
    padding: 0.2rem 0;
    transition: visibility 0.2s 0.4s, opacity 0.2s 0.4s;
    &.is-hidden {
      visibility: hidden;
      opacity: 0;
    }

    &.is-focused {
      visibility: visible;
      opacity: 1;
      transition: visibility 0.2s, opacity 0.2s;
    }

    &__button {
      font-weight: bold;
      display: inline-flex;
      background: transparent;
      border: 0;
      color: black;
      padding: 0.2rem 0.5rem;
      margin-right: 0.2rem;
      border-radius: 3px;
      cursor: pointer;

      &:hover {
        background-color: rgba(black, 0.05);
      }

      &.is-active {
        background-color: rgba(black, 0.1);
      }
    }

    span#{&}__button {
      font-size: 13.3333px;
    }
  }

  .editor__content {
    overflow-wrap: break-word;
    word-wrap: break-word;
    word-break: break-word;
    .ProseMirror {
      padding: 0.5rem;
      &:focus {
        outline: none;
      }
      pre {
        padding: 0.7rem 1rem;
        border-radius: 5px;
        background: black;
        color: white;
        font-size: 0.8rem;
        overflow-x: auto;

        code {
          display: block;
        }
      }

      p code {
        padding: 0.2rem 0.4rem;
        border-radius: 5px;
        font-size: 0.8rem;
        font-weight: bold;
        background: rgba(black, 0.1);
        color: rgba(black, 0.8);
      }
      ul,
      ol {
        padding-left: 1rem;
      }
      ul > li {
        list-style: disc;
      }

      li > p,
      li > ol,
      li > ul {
        margin: 0;
      }

      a:not(.btn) {
        color: blue;
      }
      blockquote {
        border-left: 3px solid rgba(black, 0.1);
        color: rgba(black, 0.8);
        padding-left: 0.8rem;
        font-style: italic;

        p {
          margin: 0;
        }
      }

      img {
        max-width: 100%;
        border-radius: 3px;
      }
      hr {
        border: 1px solid lightgray;
        margin: 2rem 0;
      }

      table {
        border-collapse: collapse;
        table-layout: fixed;
        width: 100%;
        margin: 0;
        overflow: hidden;

        td,
        th {
          min-width: 1em;
          border: 2px solid gray;
          padding: 3px 5px;
          vertical-align: top;
          box-sizing: border-box;
          position: relative;
          > * {
            margin-bottom: 0;
          }
        }

        th {
          font-weight: bold;
          text-align: left;
        }

        .selectedCell:after {
          z-index: 2;
          position: absolute;
          content: "";
          left: 0;
          right: 0;
          top: 0;
          bottom: 0;
          background: rgba(200, 200, 255, 0.4);
          pointer-events: none;
        }

        .column-resize-handle {
          position: absolute;
          right: -2px;
          top: 0;
          bottom: 0;
          width: 4px;
          z-index: 20;
          background-color: #adf;
          pointer-events: none;
        }
      }

      .tableWrapper {
        margin: 1em 0;
        overflow-x: auto;
      }

      .resize-cursor {
        cursor: ew-resize;
        cursor: col-resize;
      }
    }
  }
}
.tip-tap-box {
  min-height: 40rem;
}
</style>
