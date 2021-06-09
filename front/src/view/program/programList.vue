<template>
    <div>
        <navi></navi>
        <list style="margin-top: 30px"></list>
    </div>
</template>

<script>
    import list from "../../components/listP";
    import navi from "../../components/navi";
    export default {
        name: "programList",
        components:{ navi, list}
    }
</script>

<style scoped>

</style>