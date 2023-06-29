<template>
  <div class="editor">
    <editor-content class="editor__content" :editor="editor" />
  </div>
</template>

<script>
import { Editor, EditorContent } from "tiptap";
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
export default {
  components: {
    EditorContent,
  },
  props: {
    description: String,
  },
  data() {
    return {
      editor: null,
    };
  },
  mounted() {
    this.editor = new Editor({
      editable: false,
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
    });
  },

  beforeDestroy() {
    this.editor.destroy();
  },
  watch: {
    description(newDesc) {
      this.editor.setContent(newDesc);
    },
  },
};
</script>

<style></style>
