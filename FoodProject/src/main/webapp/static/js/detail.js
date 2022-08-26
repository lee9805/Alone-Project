document.addEventListener("DOMContentLoaded", () => {
  const food_table = document.querySelector("table.food");

  food_table?.addEventListener("click", (e) => {
    const target = e.target;

    const tr = target?.closest("TR");
    const seq = tr?.dataset.seq;

    console.log(seq);

    if (seq) {
      document.location.href = `${rootPath}/food/detail/${seq}`;
    }
  });
});
